globals [ NumTurtles ChanceDiseased ChanceHIV ChanceLatent ChanceActive ChanceBeginTreatment InfectionRate ActiveRate ChanceDie
  ChanceYoung ChanceAdult ChanceElderly AddChanceHIV AddChanceDiseased AddChanceYoung AddChanceOld AddChanceTreated TimeForTreatment TimeNotice ]

turtles-own [ state age hiv diseased treatment-timer noticeTB ]

to loadData
  ifelse (file-exists? "Assignment3Data.txt")
  [
   file-open "Assignment3Data.txt"
   let ignore file-read-line ;; ignores the headings of numbers, does so everytime ignore is set
   set NumTurtles file-read ;; number of people in model
   set ignore file-read-line
   set ChanceDiseased file-read ;; number of people with other diseases
   set ignore file-read-line
   set ChanceHIV file-read ;; number of people with HIV
   set ignore file-read-line
   set ChanceLatent file-read ;; number of people at start with latent TB
   set ignore file-read-line
   set ChanceActive file-read ;; number of people at start with active TB
   set ignore file-read-line
   set InfectionRate file-read ;; chance to become infected with latent TB
   set ignore file-read-line
   set ActiveRate file-read ;; chance to become infected with active TB
   set ignore file-read-line
   set ChanceBeginTreatment file-read ;; chance to start treatment while having active TB
   set ignore file-read-line
   set ChanceDie file-read ;; chance to die if not seeking treatment for active TB
   set ignore file-read-line
   set AddChanceYoung file-read ;; added chance to contract TB due to age
   set ignore file-read-line
   set AddChanceOld file-read ;; added chance to contract TB due to age
   set ignore file-read-line
   set ChanceYoung file-read ;; number of young people
   set ignore file-read-line
   set ChanceAdult file-read ;; number of adult people
   set ignore file-read-line
   set ChanceElderly file-read ;; number of old people
   set ignore file-read-line
   set AddChanceHIV file-read ;; added chance to contract TB due to HIV
   set ignore file-read-line
   set AddChanceDiseased file-read ;; added chance to contract TB due to other diseases
   set ignore file-read-line
   set AddChanceTreated file-read ;; added chance to contract TB if you've already had it
   set ignore file-read-line
   set TimeForTreatment file-read ;; time it takes to be treated for TB
   set ignore file-read-line
   set TimeNotice file-read ;; time to notice symptoms of TB
   file-close
  ]
  [
   user-message "No data file to read"
  ]
end

to setup
  clear-all
  reset-ticks
  loadData

  create-turtles NumTurtles ;; creates a set number of people in random places around the space
  ask turtles [
    setxy random-xcor random-ycor
    set shape "person"
    set state "healthy"  ;; by default most people are healthy, however some are infected with TB or other diseases that make people more susceptible to TB
    if Random 100 < ChanceLatent [set state "latent"]
    if Random 100 < ChanceActive [set state "latent-active"]
    if Random 100 < ChanceHIV [set hiv true]
    if Random 100 < ChanceDiseased [set diseased true]

    let dice random 100 ;; age bracket setup
    ifelse dice < ChanceYoung ;; 28 percent of the population is 22 or younger
      [ set age random 22 ]
      [ ifelse dice < ( ChanceYoung + ChanceElderly ) ;; 17 percent of the population is 65 or older
        [ set age random 27
          set age age + 65 ]
      [ set age random-normal 42 5 ;; 55 percent of the population are between the ages of 22 and 65
        if age > 64 [ set age 64 ]
        if age < 22 [ set age 22 ]
      ]
    ]

    set treatment-timer 0
    set NoticeTB 0
  ]

  render
end

to go
  ask turtles [ ;; the people move in random directions
    right random 25
    left random 25
    forward 1

    if not can-move? 1 ;;bounces off border
    [
       right 180
       forward 1
    ]

    if any? turtles in-radius 5 with [state = "latent-active"] ;; people avoid infected people that are displaying symptoms of TB
    [
      if state = "healthy" or state = "treated" [
        right 180
        forward 2
      ]
    ]

    let InfChance InfectionRate ;; default infection rate for healthy people
    if age >= 65 [ set InfChance InfChance + AddChanceOld ] ;; age makes people more susecptible to TB
    if age <= 22 [ set InfChance InfChance + AddChanceYoung ] ;; age makes people more susecptible to TB
    if hiv = true [ set InfChance InfChance + AddChanceHIV ] ;; HIV make people more susecptible to TB
    if diseased = true [ set InfChance InfChance + AddChanceDiseased ] ;; Other diseases make people more susecptible to TB
    if state = "latent-active" [ set InfChance -100 ] ;; People who are already infected should not be able to become latent infected again
    if state = "under-treatment" [ set InfChance -100 ] ;; People who are already infected should not be able to become latent infected again
    if state = "dead" [ set InfChance -100 ] ;; People who are dead should not be able to become latent infected again
    if state = "treated" [ set InfChance InfChance + AddChanceTreated ] ;;treated people are more succeptible to relapse
    if medeivalmode = true [ set Infchance InfChance + 30 ]
    let theradius 0
    ifelse medeivalmode = true
    [
      set theradius 4
    ]
    [
      set theradius 2
    ]
    if any? turtles in-radius theradius with [state = "latent" or state = "latent-active" ] [ ;; using the InfChance, people have a chance to become infected with TB from their neighbors
      if random 100 < InfChance [
        set state "latent"
      ]
    ]

;    if any? turtles in-radius 2 with [state = "under-treatment" ] [ ;; people under treatment for TB can still pass it on, just with less of a chance
;      if random 100 < InfChance - 10 [
;        set state "latent"
;      ]
;    ]

    let LatInfChance ActiveRate ;; default chance for latent infected people to become active infected
    if age >= 65 [ set LatInfChance LatInfChance + AddChanceYoung ] ;; age makes people more susecptible to active TB
    if age <= 22 [ set LatInfChance LatInfChance + AddChanceOld ] ;; age makes people more susecptible to active TB
    if hiv = true [ set LatInfChance LatInfChance + AddChanceHIV ] ;; HIV make people more susecptible to active TB
    if diseased = true [ set LatInfChance LatInfChance + AddChanceDiseased ] ;; Other diseases make people more susecptible to active TB
    if state = "treated" [ set LatInfChance LatInfChance + AddChanceTreated ] ;; treated people are more likely to have active TB again
    if state = "under-treatment" [ set LatInfChance -100 ] ;; People who are already infected should not be able to become active infected again
    if state = "healthy" [ set LatInfChance -100 ] ;; People who are healthy should not skip straight to latent-active
    if state = "treated" [ set LatInfChance -100 ] ;; People who are treated should not skip straight to latent-active
    if state = "dead" [ set LatInfChance -100 ] ;; People who are dead should not be able to become active infected again
    if medeivalmode = true [ set LatInfChance LatInfChance + 30 ]
    if random 100 < LatInfChance [ set state "latent-active" ] ;; using the LatInfChance, people have a chance for their latent TB to become active TB

    if state = "latent-active"
    [
    ifelse Random 100 < ChanceBeginTreatment and NoticeTB > TimeNotice and medeivalmode = false ;; people with TB  notice their symptoms and begin to seek treatment, however not all of them do
    [
      set state "under-treatment"
    ]
    [
      set NoticeTB NoticeTB + 1
    ]
    ]

    if state = "under-treatment" ;; people being treated take time to have the treatment, once completed they are treated people
    [
    ifelse treatment-timer <= TimeForTreatment
    [
      set treatment-timer treatment-timer + 1
    ]
    [
      set state "treated"
    ]
    ]

    if state = "latent-active" and NoticeTB > TimeNotice ;; people with the disease who have not yet sought treatment, might die of TB
    [
      if Random 100 < ChanceDie [ set state "dead"]
    ]
  ]

  render
  tick
end

to render
  ask turtles [
    if state = "healthy" [ set color yellow ] ;; people with no diseases in their systems
    if state = "latent" [set color orange ] ;; people with latent (no symptoms) TB bacteria in their system
    if state = "latent-active" [ set color red ] ;; people with active TB
    if state = "treated" [set color green] ;; people who have been treated for TB, however now have a higher chance than healthy people to be infected with Tb
    if state = "under-treatment" [set color blue] ;; people undergoing treatment for TB
    if state = "dead" [set color grey set shape "ghost" ] ;; people who have died from TB
  ]
end
@#$#@#$#@
GRAPHICS-WINDOW
355
-30
1051
667
-1
-1
6.812
1
10
1
1
1
0
0
0
1
-50
50
-50
50
1
1
1
ticks
30.0

BUTTON
11
15
77
48
Setup
setup
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
86
15
149
48
Go
go
T
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

MONITOR
15
67
74
112
Healthy
count turtles with [ state = \"healthy\" ]
17
1
11

MONITOR
17
123
74
168
Latent
count turtles with [ state = \"latent\" ]
17
1
11

MONITOR
20
178
77
223
Active
count turtles with [ state = \"latent-active\" ]
17
1
11

MONITOR
22
235
140
280
Under Treatment
count turtles with [ state = \"under-treatment\" ]
17
1
11

MONITOR
23
289
83
334
Treated
count turtles with [ state = \"treated\" ]
17
1
11

MONITOR
25
343
82
388
Dead
count turtles with [ state = \"dead\" ]
17
1
11

MONITOR
164
69
221
114
Young
count turtles with [ age <= 22 ]
17
1
11

MONITOR
165
128
222
173
Adult
count turtles with [ age > 22 and age < 65 ]
17
1
11

MONITOR
165
185
222
230
Elderly
count turtles with [ age >= 65 ]
17
1
11

MONITOR
164
294
221
339
HIV
count turtles with [ hiv = true ]
17
1
11

MONITOR
165
351
234
396
Diseased
count turtles with [ diseased = true ]
17
1
11

PLOT
1067
35
1412
243
Health of People
NIL
NIL
0.0
10.0
0.0
10.0
true
false
"" ""
PENS
"healthy" 1.0 0 -987046 true "" "plot count turtles with [ state = \"healthy\" ]"
"latent" 1.0 0 -955883 true "" "plot count turtles with [ state = \"latent\" ]"
"latent-active" 1.0 0 -2674135 true "" "plot count turtles with [ state = \"latent-active\" ]"
"under-treatment" 1.0 0 -13345367 true "" "plot count turtles with [ state = \"under-treatment\" ]"
"treated" 1.0 0 -13840069 true "" "plot count turtles with [ state = \"treated\" ]"
"dead" 1.0 0 -7500403 true "" "plot count turtles with [ state = \"dead\" ]"

SWITCH
22
435
168
468
medeivalmode
medeivalmode
0
1
-1000

@#$#@#$#@
## WHAT IS IT?

(a general understanding of what the model is trying to show or explain)

## HOW IT WORKS

(what rules the agents use to create the overall behavior of the model)

## HOW TO USE IT

(how to use the model, including a description of each of the items in the Interface tab)

## THINGS TO NOTICE

(suggested things for the user to notice while running the model)

## THINGS TO TRY

(suggested things for the user to try to do (move sliders, switches, etc.) with the model)

## EXTENDING THE MODEL

(suggested things to add or change in the Code tab to make the model more complicated, detailed, accurate, etc.)

## NETLOGO FEATURES

(interesting or unusual features of NetLogo that the model uses, particularly in the Code tab; or where workarounds were needed for missing features)

## RELATED MODELS

(models in the NetLogo Models Library and elsewhere which are of related interest)

## CREDITS AND REFERENCES

(a reference to the model's URL on the web if it has one, as well as any other necessary credits, citations, and links)
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

ghost
false
0
Polygon -7500403 true true 30 165 13 164 -2 149 0 135 -2 119 0 105 15 75 30 75 58 104 43 119 43 134 58 134 73 134 88 104 73 44 78 14 103 -1 193 -1 223 29 208 89 208 119 238 134 253 119 240 105 238 89 240 75 255 60 270 60 283 74 300 90 298 104 298 119 300 135 285 135 285 150 268 164 238 179 208 164 208 194 238 209 253 224 268 239 268 269 238 299 178 299 148 284 103 269 58 284 43 299 58 269 103 254 148 254 193 254 163 239 118 209 88 179 73 179 58 164
Line -16777216 false 189 253 215 253
Circle -16777216 true false 102 30 30
Polygon -16777216 true false 165 105 135 105 120 120 105 105 135 75 165 75 195 105 180 120
Circle -16777216 true false 160 30 30

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
15
Circle -1 true true 203 65 88
Circle -1 true true 70 65 162
Circle -1 true true 150 105 120
Polygon -7500403 true false 218 120 240 165 255 165 278 120
Circle -7500403 true false 214 72 67
Rectangle -1 true true 164 223 179 298
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Circle -1 true true 3 83 150
Rectangle -1 true true 65 221 80 296
Polygon -1 true true 195 285 210 285 210 240 240 210 195 210
Polygon -7500403 true false 276 85 285 105 302 99 294 83
Polygon -7500403 true false 219 85 210 105 193 99 201 83

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

wolf
false
0
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true 2 194 13 197 30 191 38 193 38 205 20 226 20 257 27 265 38 266 40 260 31 253 31 230 60 206 68 198 75 209 66 228 65 243 82 261 84 268 100 267 103 261 77 239 79 231 100 207 98 196 119 201 143 202 160 195 166 210 172 213 173 238 167 251 160 248 154 265 169 264 178 247 186 240 198 260 200 271 217 271 219 262 207 258 195 230 192 198 210 184 227 164 242 144 259 145 284 151 277 141 293 140 299 134 297 127 273 119 270 105
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
NetLogo 6.0.4
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180
@#$#@#$#@
0
@#$#@#$#@
