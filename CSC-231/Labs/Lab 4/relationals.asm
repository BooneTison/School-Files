# Illustration of relational operators using only the core instructions  beq, bne, slt
# Test two values for each of the 6 relationships 
#  ==  !=  <   >   <=   >=
# Print ONLY the appropriate relational messages
	.text
	.globl __start
__start: 
##begin by selecting two 'test' values; all relations will be checked with these two values
##then repeat by picking other test values and re-testing
	#load test values for "a" and "b", then print
	li $t0,3	#t0 is a
	li $t1,5	#t1 is b

	li $a0,4	#prepare to print prompt strings
	la $a1,astr
	syscall
	li $a0,1
	move $a1,$t0
	syscall
	
	li $a0,4
	la $a1,bstr
	syscall
	li $a0,1
	move $a1,$t1
	syscall
	
	li $a0,4	#prepare to print this string and others later(repeatedly)
	la $a1,endstr
	syscall

	##a0 remains fixed value with code for string print
	#check t0 equals t1 below  
	bne $t0,$t1,nxt0	#(this has been completed for you as an example)
	
	la $a1,eq		#display equality string
	syscall
nxt0:
	#check t0 not equal t1 below   
	beq $t0, $t1, nxt10

	la $a1,neq		#display not equal string
	syscall
nxt10:
	#check t0 < t1 below
	beq $t0, $t1, nxt20
	slt $t9, $t1, $t0
	bne $t9, $zero, nxt20

	la $a1,lt		#display less than string
	syscall
nxt20:
	#check t0 > t1 below
	beq $t0, $t1, nxt30
	slt $t9, $t0, $t1
	bne $t9, $zero, nxt30

	la $a1,gt		#display greater than string
	syscall
nxt30:	
	#check t0 <= t1 below
	slt $t9, $t1, $t0
	bne $t9, $zero, nxt40

	la $a1,lte 		#display <= string
	syscall
nxt40:	
	#check t0 >= t1 below
	slt $t9, $t0, $t1
	bne $t9, $zero, done

	la $a1,gte		#display >= string
	syscall

done:
	li $a0,10
	syscall
	
	.data
astr:	.asciiz "<<< a="
bstr:	.asciiz " b="
endstr: .asciiz " >>>\n"


gt:	.asciiz "a > b\n"
lt:	.asciiz "a < b\n"
eq:	.asciiz "a == b\n"
neq:	.asciiz "a != b\n"
gte:	.asciiz "a >= b\n"
lte:	.asciiz "a <= b\n"
