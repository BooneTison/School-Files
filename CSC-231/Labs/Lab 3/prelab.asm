# prelab.asm
# Boone Tison
# 02/12/2020

.text
.globl __start

__start:    
	############# Q1            
	la $t5, val
	lw $t0, 0($t5)
	lb $t1, 1($t5)
	lbu $t2, 1($t5)
	
	############# Q2
	li $a0, 5 # read integer
	syscall
	la $t1, arr
	sll $v0, $v0, 2 # Multiply array index by 4
	add $t2, $t1, $v0 # Add offset
	lw $t5, 0($t2) # Number to compare
	
	ori $t6, $zero, 0xf 
	bne $t5, $t6, label10 # go to else
	addi $t5, $t5, 9 # if true
	j label20 # skip else
label10:
	ori $t5, $zero, 0x5 # else t5 = 5
label20:
	addi $t2, $t2, 4 # arr[i+1]
	sw $t5, 0($t2)	
	
	############# Q3
	la $t0, t # Memory address of hello world	
	ori $t1, $zero, 0x6B # Letter k 
	ori $t2, $zero, 0x69 # Letter i
	sb $t1, 3($t0) # Change char 3 into k
	sb $t2, 4($t0) # Change char 4 into i	

	############# Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
    
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
val:	.word 0xB380C49C	
t:	.asciiz "hello world\n"
x:	.byte 5,6,7,8,9,10  
arr: 	.word 3,6,9,12,15,18,21,24
	
############### Answers
# Q1. 	$t0 = 0xb380c49c
#     	$t1 = 0xffffffc4
#	$t2 = 0x000000c4 			
