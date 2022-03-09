# stringcount.asm
# Count the number of characters in a string
# Boone Tison
# 02/11/2020

.text
.globl __start

__start:                
	############# Print Name
	la $t0, name # Memory location of name
	ori $a0, $zero, 4 # String 
	or  $a1, $zero, $t0
	syscall
	
	############# Count characters in string
	la $t1, string # Memory location of string
	ori $t2, $zero, 0 # Count of characters
topfor:	
	lb $t3, 0($t1) # Load character
	beq $t3, $zero, endfor # If char is 0, end of string
	addi $t2, $t2, 1 # Increment count
	addi $t1, $t1, 1 # Icrement location in string
	j topfor
endfor:		
	ori $a0, $zero, 1 # Integer
	or $a1, $zero, $t2
	syscall
	
	############# Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
name:	.asciiz "Boone Tison\n"
string: .asciiz "magnificent"
x:
	.byte 5,6,7,8,9,10  
