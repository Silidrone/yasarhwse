%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    mov eax, msg1
    call print_string
    xor ecx, ecx
    mov ecx, 5
    mov byte [revstr + 5], 0
    mov byte [str1 + 5], 0    
    loop0_start:
        call read_char
        mov [revstr + ecx - 1], al
        sub al, 020h
        mov ebx, str1
        sub ebx, ecx
        add ebx, 5
        mov [ebx], al

        loop loop0_start

    mov eax, msg2
    call print_string
    mov eax, revstr
    call print_string
    
    call print_nl
    
    mov eax, msg3
    call print_string
    mov eax, str1
    call print_string

    call print_nl

    leave
    ret

section .data
    msg1        db  "Enter a 5-character string: ", 0
    msg2        db  "String #1: ", 0
    msg3        db  "String #2: ", 0
section .bss
    revstr resb  6
    str1 resb    6
