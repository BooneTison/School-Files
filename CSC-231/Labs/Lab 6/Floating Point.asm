#Program to explore floating point operations
#Take a 32-bit fl.pt. number and break it into pieces
#print the result like:    - 1 & 5/128 x 2^8

####  DO NOT REDUCE FRACTIONS,  LEAVE THEM IN TERMS OF 128ths  #######


	.globl	__start
.text
__start:
	la $a1, name
	li $a0, 4
	syscall

	la  $t0, n     		#address of single precision FP value in memory
	lwc1 $f12, 0($t0)	#load into f.p. registers for confirmation later
	lw  $t0, 0($t0) 	#load value into $t0 (leave it here!)
	
        #isolate sign bit into $t1
        srl $t1, $t0, 31       
                        
     	#isolate exponent's actual value into $t2
        srl $t2, $t0, 23
        andi $t2, $t2, 0x00ff         
        addi $t2, $t2, -127    

    	#isolate significand into $t3 
	lui $t9, 0x007f
	ori $t9, $t9, 0xffff
	and $t3, $t0, $t9
	srl $t3, $t3, 16
	
	#Detect infinity
	ori $t9, $zero, 0x00ff
	bne $t2, $t9, fp5
	bne $t3, $zero, fp5
	la $a1, inf
	li $a0, 4
	syscall
	j fp20

fp5:		
	##################
	beq $t1, $zero, fp10
	li $a1, '-'
	li $a0, 11
	syscall
fp10:	                


	#The remaining code does not need changing in part 1- it will print values in t1,t2,and t3
	#The remaining code does not need changing in part 1
	li $a0,4		#print entire number as a fraction
    	la $a1,ival1    	###??? temp working solution
    	syscall

	li $a0,1		#print top: (how many 128ths)
	move $a1,$t3
	syscall
	li $a0,11		#print char: /
	li $a1,'/'
	syscall
	li $a0,1		#print:fraction bottom (128ths)
	li $a1,128
	syscall
	
	li $a0,4		#print string : exp
	la $a1,exp
	syscall
	li $a0,1		#print int: exponent value 
	move $a1,$t2
	syscall
	
	li $a0,4		#print separation
	la $a1,sep       	#
	syscall

	li $a0,2		#print as a float as confirmation of original value
	syscall
	
fp20:	
	li $a0,10
	syscall

	.data
n:	.float -0.05
anything: .word 0x789A0000

ival1:  .asciiz "1 & "
exp:    .asciiz " x 2^"
sep:    .asciiz " == "

ival0:  .asciiz "0 & "

inf:	.asciiz "infinity"
name:	.asciiz "Boone Tison\n"
