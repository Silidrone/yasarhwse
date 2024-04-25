%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    xor eax, eax
    
    mov al, [n1]
    mov ah, [n2]

    cmp al, ah
    
    leave
    ret

section .data
    n1		db  0F3h
    n2		db  019h
