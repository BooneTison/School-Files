#Program Homework fibonacci calculation procedure
#This is the 'main' calling program;  you may not change anything between
# start and end (except print your name)
# Boone Tison - 02/20/2020
	.globl	__start
.text
__start:
####add code to print your name from prompt below####
	la $t0, name
	li $a0, 4 # Print string
	move $a1, $t0
	syscall

##  Display fib(3)
	li $s0, 3	#run test value
	move $a0,$s0
	jal printPrompt
	move $a0,$s0
	jal  fib
	move  $a1,$v0  	#print results
	li $a0, 1
	syscall

##  Display fib(14) 
	li $s0, 14	#run test value
	move $a0,$s0
	jal printPrompt
	move $a0,$s0
	jal  fib
	move  $a1,$v0  	#print results
	li $a0, 1
	syscall

	li $a0, 10
__exit:	syscall


#Function: print value and prompt
printPrompt:
	move $t1,$a0
	li $a0,11
	li $a1,'\n'	 #newline
	syscall
	move $a1,$t1	  #print number value
	li  $a0,1
	syscall
	la $a1, numPrompt #print prompt
	li $a0, 4
	syscall
	jr $ra

######
######add your fib function code below this line
fib:
	slt $t9, $a0, $zero # Check n >= 0
	bne $t9, $zero, invalidN
	li $t0, 0 # a
	li $v0, 1 # b
	li $t2, 2 # i
	li $t3, 0 # c 
startFor:
	slt $t9, $a0, $t2 # Check i <= n
	bne $t9, $zero, end	
	add $t3, $t0, $v0 # c = a+b
	move $t0, $v0 # a = b
	move $v0, $t3 # b = c
	addi $t2, $t2, 1 # i++
	j startFor
	
invalidN:
	li $v0, 0
end:			
	jr $ra



.data
name:	.asciiz "Boone Tison\n"
numPrompt:
 	.asciiz " calculates to:\t"
	
