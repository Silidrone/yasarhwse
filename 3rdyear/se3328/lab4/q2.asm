%include "linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    mov byte [output1 + 5], 0
    mov byte [output2 + 5], 0

    mov eax, msg1
    call print_string

    call read_char
    mov [output1], al
    sub byte [output1], 32
    mov [output2 + 4], al

    call read_char
    mov [output1 + 1], al
    sub byte [output1 + 1], 32
    mov [output2 + 3], al

    call read_char
    mov [output1 + 2], al
    sub byte [output1 + 2], 32
    mov [output2 + 2], al

    call read_char
    mov [output1 + 3], al
    sub byte [output1 + 3], 32
    mov [output2 + 1], al

    call read_char
    mov [output1 + 4], al
    sub byte [output1 + 4], 32
    mov [output2], al

    mov eax, msg2
    call print_string

    mov eax, output2
    call print_string
    call print_nl

    mov eax, msg3
    call print_string

    mov eax, output1
    call print_string
    call print_nl

    call print_nl

    leave
    ret

section .data
    msg1        db  "Enter a 5-character string: ", 0
    msg2        db  "String #1: ", 0
    msg3        db  "String #2: ", 0
section .bss
    output1       resb    6 ; reverse output
    output2       resb    6 ; capital output
