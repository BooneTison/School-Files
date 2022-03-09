# DIY.asm
# Boone Tison
# 01-29-2020
# DIY goals:
# Output our names, add two values and print it
# Read integer from user, add 2000, print them both
# Check if integer is odd or even

.text
.globl __start

__start:                
	
	############# Names
	ori $t0, $zero, 0x1001 # memory location of word 
	sll $t0, $t0, 16
	ori $t0, $t0, 0x003b
	ori $a0, $zero, 4 # String of names
	or  $a1, $zero, $t0
	syscall
	
	############# DIY #1
	ori $t1, $zero, 0x1001 
	sll $t1, $t1, 16
	ori $t1, $t1, 0x0024
	lw $t2, 0($t1) # val 0
	lw $t3, 4($t1) # val 1
	
	add $t4, $t2, $t3 # add val 0 and val 1
	ori $a0, $zero, 1
	or $a1, $zero, $t4 # print $t4
	syscall 
	
	ori $a0, $zero, 11 # newline after integer
	ori $a1, $zero, '\n'
	syscall
	
	############# DIY #2 
	ori $a0, $zero, 5 # 1. Read integer from user
	or $a1, $zero, $v0
	syscall
	
	ori $a0, $zero, 1 # 2. Print integer from user
	or $a1, $zero, $v0
	syscall
	
	ori $a0, $zero, 11 # 3. New line
	ori $a1, $zero, '\n'
	syscall
	
	addi $t5, $v0, 0x7D0 # 3. add 2000 to user integer
	ori $a0, $zero, 1
	or $a1, $zero, $t5
	syscall
	
	############# DIY #3
	andi $t6, $v0, 0x00000001
	
	############# Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
	.asciiz "Boone and Ethan\n"
x:
	.byte 5,6,7,8,9,10  
