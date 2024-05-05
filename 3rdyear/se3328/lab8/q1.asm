%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp
    
    mov ebx, 007FFFFFFFh
    start_while:
        mov eax, msg
        call print_string
        call read_int
        cmp eax, 0
        je stop_while
        cmp eax, ebx
        jl assign
        jmp start_while
        
    assign:
	    mov ebx, eax
        jmp start_while
    
    stop_while:
    	mov eax, ebx
	    call print_int
    
    leave
    ret
    
section .data
    msg    db "Enter an integer: ", 0

section .bss
    input1 resd 1
