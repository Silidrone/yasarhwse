%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    xor eax, eax

    mov ax, [n1]
    add ax, [n2]
    add ax, [n3]
    call print_int
    call print_nl
    
    leave
    ret

section .data
    n1		dw  01234h
    n2		dw  02255h
    n3		dw  04567h
