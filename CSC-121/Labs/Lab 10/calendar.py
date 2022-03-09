# Calendar.py - Calendar functions

import re

def isLeapYear(y):
    if y % 100 == 0:
        if y % 400 == 0:
            return True
        else:
            return False
    elif y % 4 == 0:
        return True
    else:
        return False

def findMonthLength(m, y):
    if m == 1 or m == 3 or m == 5 or m == 7 or m == 9 or m == 10 or m == 12:
        return 31
    elif m == 4 or m == 6 or m == 8 or m == 11:
        return 30
    elif m == 2:
        if isLeapYear(y):
            return 29
        else:
            return 28

def findJulianDay(m, d, y):
    if m != 1:
        total = 0
        for i in range(1,m):
            total += findMonthLength(i,y)
        total += d
        return total
    else:
        return d
    
def findZodiac(m, d):
    value = 100 * m + day
    if value >= 321 and value <= 419:
        return "Aries"
    elif value >= 420 and value <= 520:
        return "Taurus"
    elif value >= 521 and value <= 620:
        return "Gemini"
    elif value >= 621 and value <= 722:
        return "Cancer"
    elif value >= 723 and value <= 822:
        return "Leo"
    elif value >= 823 and value <= 922:
        return "Virgo"
    elif value >= 923 and value <= 1022:
        return "Libra"
    elif value >= 1023 and value <= 1121:
        return "Scorpio"
    elif value >= 1122 and value <= 1221:
        return "Sagittarius"
    elif value >= 120 and value <= 218:
        return "Aquarius"
    elif value >= 219 and value <= 320:
        return "Pisces"
    else:
        return "Capricorn"

# Horoscopes from the New York Daily News on March 26, 2019
def findHoroscope(sign):
    if sign == "Aries":
        return "Spending time in the company of a tried-and-true friend " + \
               "prompts you to consider confiding something quite personal " + \
               "today. Sharing secrets is a powerful bonding experience you " + \
               "tend to reserve for special occasions, and rightfully so. " + \
               "But if your gut tells you the time is right, go ahead and " + \
               "spill the beans. The supportive response you may receive " + \
               "soothes your soul and convinces you that being emotionally " + \
               "available might not be such a bad idea after all. " + \
               "Singer Alanis Morissette said, 'I found that the more " + \
               "truthful and vulnerable I was, the more empowering it was " + \
               "for me.'"
    elif sign == "Taurus":
        return "A random act of kindness allows you to see the soft side " + \
               "of an acquaintance you suspect could eventually become a " + \
               "good friend. Take this opportunity to get to know them " + \
               "better, even if you only have time for a cup of coffee or " + \
               "a quick bite today. Telling someone you've noticed and " + \
               "appreciated the good deeds they've done warms their spirit. " + \
               "More importantly, your conversation inspires you both to " + \
               "create inventive ways to turn generosity and compassion " + \
               "into a regular habit. There are no limits to what you can " + \
               "accomplish if you put your heads and hearts together."
    elif sign == "Gemini":
        return "All that time you've spent ruminating over things lately " + \
               "is paying off. The first signs of tangible success may " + \
               "surface today. A situation or event requires you to " + \
               "declare your alliance to a way of life you can confidently " + \
               "commit to for the long haul. Presenting yourself to a " + \
               "professional community is much easier when you know who you " + \
               "really are at your core. Others are more prone to seeing " + \
               "your potential for stardom when you believe in yourself. " + \
               "Strut your stuff, with no apologies or regrets."
    elif sign == "Cancer":
        return "You may accidentally stumble across a belief system that " + \
               "feels familiar, yet is entirely different from what " + \
               "you're accustomed to practicing currently or in the past. " + \
               "If this surreal experience also seems like a spiritual " + \
               "discovery, there's no way to brush it off or pretend it's " + \
               "just a passing fancy. There are a few precious times in " + \
               "your life when you find something that causes your spirit " + \
               "to smile and say, 'This is mine. I claim it as my own.' " + \
               "Cherish this moment as the cosmos connects you with your " + \
               "true foundation."
    elif sign == "Leo":
        return "The overly dramatic behavior of a dear one has you " + \
               "looking around furtively for a quick escape route. " + \
               "Sure, you're prone to stirring things up occasionally " + \
               "to keep your audience entertained, but it's all " + \
               "stagecraft. You know it and they know it. However, " + \
               "when it comes to melodrama, you just won't stand for it " + \
               "today. You're nothing if not proud, and you refuse to " + \
               "suffer embarrassing fools. Should someone come even " + \
               "close to that line in the sand, act without hesitation. " + \
               "The more you love yourself, the less nonsense you tolerate."
    elif sign == "Virgo":
        return "That old familiar urge to fix, save, or rescue someone " + \
               "resurfaces now. The astrological culprit, loving Venus, " + \
               "wanders into selfless Pisces and your 7th House of " + \
               "One-to-One Relationships, making it simultaneously " + \
               "imperative and impossible to set up personal boundaries. " + \
               "All you can safely offer now without depleting your own " + \
               "essential resources is your spiritual advice and emotional " + \
               "comfort. Be careful not to promise too much. Author " + \
               "Irma Kurtz wrote, 'Givers have to set limits because " + \
               "takers rarely do.'"
    elif sign == "Libra":
        return "Your sincere concern for others is one of your best traits. " + \
               "For example, you may be worrying about a coworker's " + \
               "wellbeing if they are absent for several days. You tend " + \
               "to assume the worst but will be thrilled if you discover " + \
               "you are wrong. There's only one way to find out what's " + \
               "happening now. Call your missing friend to check in, or " + \
               "drop off some soup and flowers at the door. You might be " + \
               "overreaching, but you can also be doing them a huge favor. " + \
               "President Ronald Reagan said, 'We can't help everyone, " + \
               "but everyone can help someone.'"
    elif sign == "Scorpio":
        return "You may replay the siren's song of a childhood interest or " + \
               "an old pastime as if it was part of your current life. " + \
               "The tune grows louder now, thanks to sweet Venus dancing " + \
               "into your 5th House of Creative Pursuits, gently reminding " + \
               "you that you are not devoting enough time to your " + \
               "unrealized aspirations. The good news is you still have " + \
               "what it takes to make them come true. Colin Powell said, " + \
               "'A dream doesn't become reality through magic. It takes " + \
               "sweat, determination, and hard work.'"
    elif sign == "Sagittarius":
        return "Forgiveness is always a relief, for both the offender " + \
               "and the offended party. Before you decide whether or " + \
               "not it's time to extend your compassion to a family " + \
               "member who was less than charitable to you, allow " + \
               "yourself the luxury of celebrating how far you've " + \
               "traveled without them. Thank the dear friends who " + \
               "stepped up and always had your back, making sure you were " + \
               "strong enough to go on. Blood connections only mean that " + \
               "you're biologically related; loyalty means you are family " + \
               "by choice. There's no such thing as a strong person with " + \
               "an easy past, but most strong people have a loyal ally " + \
               "in their corner."
    elif sign == "Capricorn":
        return "It's only natural to keep something secret if you're " + \
               "not sure anyone wants to hear about it. However, you " + \
               "probably realize that you know people who care enough to " + \
               "listen when you need to talk. In fact, there's likely " + \
               "someone out there who will quite happily chat with you " + \
               "about nothing of consequence until you finally decide " + \
               "you're ready to reveal all. At that point, they will put " + \
               "your fears to rest, offer their shoulder, and maybe even " + \
               "share something personal of their own. Those who care " + \
               "about you always hear you, even when you're being quiet."
    elif sign == "Aquarius":
        return "Keep a close eye on your money, and not just in a " + \
               "figurative sense. Take time to review and reorganize " + \
               "your finances; if you need help, ask a friend who's far " + \
               "fiscally wiser than you to explain the details. But it's " + \
               "also critical to watch your cash flow now that Venus is " + \
               "drifting through dreamy Pisces and your 2nd House of " + \
               "Net Worth, making it easy to become distracted or " + \
               "dazzled. The real treasures are not found in a fancy " + \
               "boutique or a thrift store. Leave your wallet safely at " + \
               "home and you will have no reason for remorse or regret."
    elif sign == "Pisces":
        return "Encounters with kindred spirits feel especially magical " + \
               "and mystical to you now. Vulnerable Venus tiptoes into " + \
               "your empathetic sign and your 1st House of Personality, " + \
               "turning you into a veritable sponge. Sponges soak up all " + \
               "kinds of things, so it's vital to your emotional health " + \
               "that you engage with only the most positive people. " + \
               "The moment you sense negative energy is the moment " + \
               "you need to go. Don't waste time trying to think up " + \
               "an excuse when a polite goodbye is all that's necessary. " + \
               "Surround yourself with those who are in sync with your heart."
    else:
        return "Invalid sign for horoscope."
# -----------------------------------------------------------------------
# main program

# Test the calendar functions
print("Test of leap year:")
print("1900 was a leap year:  ", isLeapYear(1900))
print("2000 was a leap year:  ", isLeapYear(2000))
print("2004 was a leap year:  ", isLeapYear(2004))
print("2005 was a leap year:  ", isLeapYear(2005))

print("\nTest of findMonthLength:")
for i in range(1, 13):
    print("{0:2d}/2016 has {1:d} days.     {2:2d}/2019 has {3:d} days.". \
          format(i, findMonthLength(i, 2016), i, findMonthLength(i, 2019)))

print("\nTest of findJulianDay:")
for i in range(1, 13):
    print("{0:2d}/1/2016 --> {1:3d}     {2:2d}/1/2019 --> {3:3d}". \
          format(i, findJulianDay(i, 1, 2016), i, findJulianDay(i, 1, 2019)))

print("\nTest of findZodiac:")
for i in range(1, 13):
    print("{0:d}/{1:d} has the sign {2:s}.". \
          format(i, 1, findZodiac(i, 1)))


# Let's enjoy some interactive I/O
date = input("Enter a date of the form mm/dd/yyyy:  ")
tok = re.compile("/")
tokenlist = tok.split(date)
tokenlist = [ s for s in tokenlist if len(s) > 0 ]
month = int(tokenlist[0])
day = int(tokenlist[1])
year = int(tokenlist[2])

# Let's call the various calendar functions
# YOU NEED TO COMPLETE THE ASSIGNMENT STATEMENTS
leapYear = isLeapYear(year)
daysInMonth = findMonthLength(month,year)
julianDay = findJulianDay(month,day,year)
sign = findZodiac(month,day)
horoscope = findHoroscope(sign)

# Output
print("Is it a leap year?", leapYear)
print("The month has {0:d} days.".format(daysInMonth))
print("The Julian day is {0:d}.".format(julianDay))
print("The sign of the Zodiac is {0:s}.".format(sign))
print("\nHere is the horoscope for that birthday:\n{0:s}\n".format(horoscope))
