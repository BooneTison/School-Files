# Simple demonstration code:
# This is a simple example assembly program 
# This file can also be used as a template for future programs

# Bryan Catron		January 2019

.text
.globl __start

__start:                	# begin assembly program

	ori $t1, $zero, 15	#logical OR operation with a constant value

	ori $t2, $t1, 345

	and $t0, $t1, $t2	#logical AND

	xor $t0, $t0, $t1	#logical XOR
	

##################      
 
#### finished calculations, print and exit
	or $a1,$t0,$zero

	ori $a0,$zero,1        	#code to print integer in $a0
	syscall

	ori $a0,$zero,10	#clean exit
	syscall
    
    
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
x:
	.byte 5,6,7,8,9,10
