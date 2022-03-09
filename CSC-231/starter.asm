# Simple starter file:
# Add your name, date, and program overview

.text
.globl __start

__start:                
	
	# your code goes here




	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
x:
	.byte 5,6,7,8,9,10  