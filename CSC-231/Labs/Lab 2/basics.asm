# Lab 2: Labels, arrays, Memory and more
# Boone Tison - 02/05/2020
.text
.globl __start
__start:
	################### Lab 2.1
	lui $t0, 0x1001 # Memory address of names
	ori $t0, $t0, 0x0039 
	ori $a0, $zero, 4 # Print string
	or $a1, $zero, $t0
	syscall 
	
	################### Lab 2.2
	ori $a0, $zero, 5 # User inputs integer
	or $a1, $zero, $v0
	syscall
	
	lui $t1, 0x1001 # Memory address of arr[0]
	ori $t1, $t1, 0x0008
	sll $v0, $v0, 2 # Multiplies n by 4, 4 bits to 4 words
	add $t1, $t1, $v0 # Adds offset given by user

	lw $t2, 12($t1) # arr[n+3]
	lw $t3, 16($t1) # arr[n+4]
	add $t4, $t2, $t3
	
	lui $t5, 0x1001 # Empty memory location
	ori $t5, $t5, 0x0074
	sw $t4, 0($t5)
	
	ori $a0, $zero, 1 # Print integer
	or $a1, $zero, $t5
	syscall 
	
	################### Lab 2.3
	ori $a0, $zero, 11 # newline after integer
	ori $a1, $zero, '\n'
	syscall
	
	srl $v0, $v0, 2 # Divides by 4 to get original integer
	andi $t6, $v0, 0x00000001 # Checks if even or odd
	
	bne $t6, $zero, numIsOdd
	lui $t7, 0x1001 # If even, find even string
	ori $t7, $t7, 0x00db
	j endIf # Jump past odd string finding
	
numIsOdd:
	lui $t7, 0x1001 # If odd, find odd string
	ori $t7, $t7, 0x00cc

endIf:		
	ori $a0, $zero, 4 # Print string
	or $a1, $zero, $t7
	syscall 
	

############# clean exit #######
	ori $a0, $zero, 10
	syscall
     
.data  
myX:    .word -4, 4
arr:	.word 9, 10, 11, 12, 13, 14, 15, 16, 249
	.word 0x1290A0B0, 0x01234567, 0x44556611
	.byte 8
prompt1:
	.asciiz "Boone and James\n"
	.byte 5, 6, 7
prompt2:
	.ascii  "not null terminated?"
	.byte 126, 64, 123, 94, 58,95,'b'
mySpace:
	.space 100
prompt3:
	.asciiz "Number is odd\n"
	.asciiz "Even Steven\n"	