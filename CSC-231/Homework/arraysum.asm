# arraysum.asm
# Sum the integers in a 5 element array
# Boone Tison
# 02/24/2020

.text
.globl __start

__start:                
	############# Print Name
	la $t0, name # Memory location of name
	ori $a0, $zero, 4 # String 
	or  $a1, $zero, $t0
	syscall

	############# Sum 5 element array
	la $a0, arr  # Memory location of array
	jal sum5Int
	move $a1, $v0
	ori $a0, $zero, 1 # Integer
	syscall
	
	############# Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
	
	############# Sum5
sum5Int:
	ori $v0, $zero, 0 # Sum
	ori $t1, $zero, 5 # Count 
sum5Int10:
	beq $t1, $zero, sum5Int20 # 5 to 0 for loop
	lbu $t9, 0($a0) # Get number from array
	add $v0, $v0, $t9
	addi $t1, $t1, -1 # Increment loop
	addi $a0, $a0, 1 # Increment array location
	j sum5Int10
sum5Int20:
	jr $ra		
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
t:	.asciiz "hello - world\n"
name:	.asciiz "Boone Tison\n"
arr:
	.byte 4,2,45,100,250,9,14,30,40,1,5,9
