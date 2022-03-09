# vowelTrasher.asm
# Given a string, store it backwards while ingoring vowels
# Boone Tison - 03/02/2020

.text
.globl __start

__start:                
	############# Main
	la $a0, name
	la $a1, dst
	jal vowelTrasher
	la $a1, dst
	li $a0,4
	syscall

	############# Clean Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
	
##################### Methods
isVowel:
	li $t0, 'a'
    	beq $a0, $t0, vowelTrue # If vowel, go to true
    	li $t0, 'e' # Check next vowel
    	beq $a0, $t0, vowelTrue
    	li $t0, 'i'
    	beq $a0, $t0, vowelTrue
    	li $t0, 'o'
    	beq $a0, $t0, vowelTrue
    	li $t0, 'u'
    	beq $a0, $t0, vowelTrue
    	li $v0, 0 # Return false
    	j vowel10
vowelTrue:
	li $v0, 1 # Return true   
vowel10:	
	jr $ra 	 
	
vowelTrasher:
	addi $sp, $sp, -16 # Store four numbers
	sw $ra, 12($sp) # Store ra
vt10:
	sw $a0, 0($sp) # Store source
	sw $a1, 4($sp) # Store destination
	lbu $a0, 0($a0) # Get character
	beq $a0, $zero, vt20
	sb $a0, 8($sp) # Store char
	jal isVowel
	bne $v0, $zero, vtVowel
	lw $a1, 4($sp) # Load dst
	lw $t0, 8($sp) # Load char
	sb $t0, 0($a1) # Store char at dst
	lw $a0, 0($sp) # Get source
	addi $a0, $a0, 1 # Increment source
	addi $a1, $a1, 1 # Increment dst
	j vt10
vtVowel:	
	lw $a0, 0($sp) # Get source
	addi $a0, $a0, 1 # Increment source
	j vt10
vt20:
	lw $ra, 12($sp)
	addi $sp, $sp, 16
	jr $ra	
		
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
name:	.asciiz "Boone Tison\n"
dst:	.asciiz ""
x:
	.byte 5,6,7,8,9,10  
