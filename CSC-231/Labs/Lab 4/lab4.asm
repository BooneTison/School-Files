# lab4.asm
# Boone Tison 
# 02/19/2020

.text
.globl __start

__start:                
	############## Print Names
	la $a1, name
	ori $a0, $zero, 4
	syscall
	
	############## Read char from user
	li $a0, 12
	syscall
	or $s0, $zero, $v0
	jal printNewline
	
	############## Main
	or $a0, $zero, $s0
	jal isAlpha
	beq $v0, $zero, integer
	or $a0, $zero, $s0
	jal isUpper
	bne $v0, $zero, printChar
	andi $v0, $v0, 0xDF
	
printChar:
	or $a1, $zero, $v0
	ori $a0, $zero, 11
	syscall
	jal printNewline
	j end
	
integer:
	or $a0, $zero, $s0
	addi $a0, $a0, -0x30
	jal abs
	or $a1, $zero, $v0
	ori $a0, $zero, 1
	syscall
	jal printNewline
	
end: 
	la $a0, name
	jal strlen
	or $a1, $zero, $v0
	ori $a0, $zero, 1
	syscall
	
	############## Clean Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
	
isUpper:
	andi $v0, $a0, 0x10
	srl $v0, $v0, 5
	beq $v0, $zero, invert
	li $v0, 0x0
	jr $ra
invert:
	li $v0, 0x1
	jr $ra
	
isAlpha:
	addi $a0, $a0, -0x61
	sltiu $v0, $a0, 27
	jr $ra
	
abs:
	andi $t0, $a0, 0x80
	beq $t0, $zero, positive
	nor $a0, $a0, $zero
	addi $v0, $a0, 1
	jr $ra 
positive:
	or $v0, $zero, $a0
    	jr $ra

strlen:
	ori $v0, $zero, 0 # Count of characters
topfor:	
	lb $t1, 0($a0) # Load character
	beq $t1, $zero, endfor # If char is 0, end of string
	addi $v0, $v0, 1 # Increment count
	addi $a0, $a0, 1 # Icrement location in string
	j topfor
endfor:
	jr $ra
	
printNewline:
	ori $a1, $zero, '\n'
	ori $a0, $zero, 11
	syscall
	jr $ra
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
name:	.asciiz "Boone Tison and James Keith\n"
x:
	.byte 5,6,7,8,9,10  
