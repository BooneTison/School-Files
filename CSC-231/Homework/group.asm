# Group.asm
# Boone Tison - 01/29/2020
# Given a integer,
# Assign it to a group and subgroup

.text
.globl __start

__start:                
	
	############# Print name
	ori $t0, $zero, 0x1001 # memory location of word 
	sll $t0, $t0, 16
	ori $t0, $t0, 0x0033 # second half of memory location
	ori $a0, $zero, 4 # String 
	or  $a1, $zero, $t0
	syscall
	
	############# Read user integer
	ori $a0, $zero, 5 
	or $a1, $zero, $v0
	syscall
	
	############# Group
	ori $t1, $zero, 0x1001 # Print group
	sll $t1, $t1, 16
	ori $t1, $t1, 0x0040
	ori $a0, $zero, 4 # String 
	or  $a1, $zero, $t1
	syscall
	
	add $t3, $zero, $v0 # Group number
	srl $t3, $t3, 4 # shifts right, if < 16, now zero
	andi $t3, $t3, 0x00000001 # if > 16, sets to 1
	ori $a0, $zero, 1
	or $a1, $zero, $t3
	syscall
	
	ori $a0, $zero, 11 # newline after integer
	ori $a1, $zero, '\n'
	syscall
	
	############# Subgroup
	ori $t2, $zero, 0x1001 # Print subgroup
	sll $t2, $t2, 16
	ori $t2, $t2, 0x0048
	ori $a0, $zero, 4 # String
	or $a1, $zero, $t2
	syscall
	
	add $t4, $zero, $v0 # Subgroup number 
	andi $t4, $t4, 0x0F # Clears all bits greater than 2^4
	ori $a0, $zero, 1
	or $a1, $zero, $t4
	syscall

	############# Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	
t:	.asciiz "hello - world\n"
	.asciiz "Boone Tison\n"
	.asciiz "Group: "
	.asciiz "Subgroup: "
x:
	.byte 5,6,7,8,9,10  
