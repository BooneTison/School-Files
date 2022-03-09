#Program to explore floating point operations
#Complete the missing code to check for right triangle measurements based on the Pythagorean theorem
	.globl	__start
.text
__start:
	#Print your name
        la $a1, name
        li $a0, 4
        syscall

   	#load address of triangle sides 
    	la $a0, vals
	jal pythag
	
	beq $v0, $zero, prtTriangle 
	
err:			#print error string and quit
    	la $a1,errstring
    	j prt10

prtTriangle:    	#print valid Triangle string
    	la $a1,tristring

prt10:           	#secondary label to print something & quit
    	li $a0, 4         
    	syscall
exit:
    	li $a0,10	#exit call
    	syscall


##########
#PYTHAG   performs checks on data
#Load from memory starting at @a0 into registers $f0 <= a, $f2<= b, $f4 <= c
#if c is not the largest value return false
#check a^2 + b^2 = c^2
#return true if this is right triangle; false otherwise
pythag:
	ldc1 $f0, 0($a0) # a
	ldc1 $f2, 8($a0) # b
	ldc1 $f4, 16($a0) # c
	
	c.lt.d $f4, $f0 # Check if c is less than a or b
	bc1t pythagFalse
	c.lt.d $f4, $f2
	bc1t pythagFalse
	
	mul.d $f0, $f0, $f0 # Square a,b,c
	mul.d $f2, $f2, $f2
	mul.d $f4, $f4, $f4
	add.d $f6, $f0, $f2 # Add a^2 and b^2
	c.eq.d $f4, $f6
	bc1t pythagTrue
pythagFalse:
	ori $v0, $zero, 0xffff
	j pythagExit
pythagTrue:
	li $v0, 0
pythagExit:
	jr $ra	

	.data
vals:	.double 7.0
    	.double 8.0
    	.double 10.00000000000000001
tristring: .asciiz "Right Triangle!"
errstring: .asciiz "Not a right triangle"
name:	.asciiz "Boone Tison\n"	