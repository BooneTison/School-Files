# char.asm
# Read in and store a series of characters
# Boone Tison
# 02/12/2020

.text
.globl __start

__start:                
	la $t0, arr # Location to store characters
	ori $t1, $zero, 0x78 # Termination char

toploop:		
	li $a0, 12 # read char
	syscall
	beq $v0, $t1, termloop # If x has been entered
	sb $v0, 0($t0) # Heap to store in
	addi $t0, $t0, 1 # Next bit in array
	j toploop

termloop:	
	sb $zero, 1($t0) # Null terminate
	
	############# Print newline
	ori $a0, $zero, 11 # newline 
	ori $a1, $zero, '\n'
	syscall
	
	############# Print Name
	la $t8, name 
	li $a0, 4 # String
	or $a1, $zero, $t8
	syscall 
	
	############# Print array
	la $t0, arr
	li $a0, 4 # String
	or $a1, $zero, $t0
	syscall

	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
t:	.asciiz "hello - world\n"
name:	.asciiz "Boone Tison\n"
x: 	.byte 5,6,7,8,9,10  
arr:	.byte 1	
