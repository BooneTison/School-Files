# In Class Activity
# Boone Tison, 01/27/2020
# Loads a 32-bit value into a register

.text
.globl __start

__start:                
	
	ori $t5, $zero, 0x3AD4
	sll $t5, $t5, 16
	ori $t5, $t5, 0x6e01 
	ori $a0, $zero, 1 	# system call code to print an integer
	or  $a1, $zero, $t5 	# move $t5 value
	syscall
	
	ori $t1, $zero, 0x1001 	# first half of memory
	sll $t1, $t1, 16	# moves half left
	ori $t1, $t1, 0x002c	# adds second half
	ori $a0, $zero, 4	# system call to print a string
	or  $a1, $zero, $t1	# move $t1 value
	syscall
	
	ori $t2, $zero, 0x1001	# first half of memory
	sll $t2, $t2, 16	# moves half left
	ori $t2, $t2, 0x003b	# adds second half
	ori $a0, $zero, 4	# system call to print a string
	or  $a1, $zero, $t2	# move $t2 value
	syscall
	
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
	.asciiz "Boone and James\n"
x:
	.byte 5,6,7,8,9,10

############## Answers
# 3b. 0x004004
# 3c. 0x004008
# 3d. Acts as a step counter by a bit
# 4. 0x3ad4000
# 5. 0x3ad46e01
# 6. 987,000,321
# 8. Each instruction has a max of 32 bits, the whole memory address is already 32-bits 		
