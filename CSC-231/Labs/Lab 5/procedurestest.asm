# procedures.asm
# Boone Tison - 02/26/2020
# Practicing non-leaf procedures with a string

.text
.globl __start

__start:                
	############# Print Name
	la $a1, name
	li $a0, 4 # String
	syscall
	
	############# Main
	la $s0, test # Address of testing string
	move $a1, $s0
	syscall
	
	############# Length
	move $a0, $s0 # Address of a string
	jal length
	move $a1, $v0
	li $a0, 1
	syscall
	
	############# charAt
	move $a0, $s0 # Address of a string
	li $a1, 0 # Index 
	jal charAt
	move $a1, $v0
	li $a0, 11
	syscall
	
	############# indexOf
	move $a0, $s0 # Address of a string
	li $a1, 'y' # A character
	jal indexOf
	move $a1, $v0
	li $a0, 1
	syscall

	############# Clean Exit
	ori $a0,$zero,10	#clean exit - stops PC properly!
	syscall
	
############### Functions 	

length:
	li $v0, 0 # Count
length10:	
	lbu $t9, 0($a0)
	beq $t9, $zero, length20
	addi $v0, $v0, 1 # Increment length
	addi $a0, $a0, 1 # Increment address
	j length10
length20:	
	jr $ra

charAt:
	addi $sp, $sp, -8
	sw $a0, 0($sp) # Store string address
	sw $ra, 4($sp) # Store return address
	jal length
	slt $t0, $a1, $v0 # If index is < length
	beq $t0, $zero, chAtInvalid
	li $t0, 0 # Current pos
	lw $a0, 0($sp) # Load string address
chAt10:	
	lbu $v0, 0($a0)
	beq $t0, $a1, chAt20
	addi $t0, $t0, 1
	addi $a0, $a0, 1
	j chAt10
chAtInvalid:
	li $v0, 0 # Return null if invalid index
chAt20:
	lw $ra, 4($sp) # Load original return address
	addi $sp, $sp, 8
	jr $ra
	
indexOf:
	addi $sp, $sp, -8
	sw $a0, 0($sp) # Store string address
	sw $ra, 4($sp) # Store return address
	jal length
	move $t9, $v0 # End of string
	lw $a0, 0($sp) # Load string address
	li $v0, 0 # Current pos
indexOf10:
	beq $t9, $v0, indexOfEnd # If reached end of string
	lbu $t5, 0($a0) 
	beq $t5, $a1, indexOf20 # If char found
	addi $v0, $v0, 1
	addi $a0, $a0, 1
	j indexOf10
indexOfEnd:			
	li $v0, -1
indexOf20:	
	lw $ra, 4($sp) # Load original return address
	addi $sp, $sp, 8
	jr $ra
      
############### Data Segment  
.data    # at default data segment address
    	.word 9,10,11,12,13,14,15,16,17
vals:
	.word 0x01234567, 0x00AF0001
t:	.asciiz "hello - world\n"
test:	.asciiz "zebra test\n"
name:	.asciiz "Boone Tison\n"
x:
	.byte 5,6,7,8,9,10  
