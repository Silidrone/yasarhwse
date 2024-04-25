%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    mov eax, msg1
    call print_string
    call read_int
    mov [n1], al

    mov eax, msg1
    call print_string
    call read_int
    mov [n2], al

    mov eax, msg1
    call print_string
    call read_int
    mov [n3], al

    mov al, [n1]
    call print_char
    call print_nl
    
    mov al, [n2]
    call print_char
    call print_nl

    mov al, [n3]
    call print_char
    call print_nl
    
    leave
    ret

section .data
    msg1    db  "Please enter an integer: ", 0
section .bss    
    n1		resb  1
    n2		resb  1
    n3		resb  1
