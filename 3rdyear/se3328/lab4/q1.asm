%include "linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    mov eax, msg1
    call print_string

    call read_char
    mov [input1], al

    mov eax, msg2
    call print_string

    call read_int
    mov [input2], eax

    mov eax, msg3
    call print_string

    mov al, [input1]
    call print_char

    call print_nl

    mov eax, msg4
    call print_string
        
    mov eax, [input2]
    call print_int

    call print_nl

    leave
    ret

section .data
    msg1        db  "Enter a character: ", 0
    msg2        db  "Enter an integer: ", 0
    msg3        db  "The character entered was: ", 0
    msg4        db  "The integer entered was: ", 0
section .bss
    input1  resb    1   ; character input
	input2	resd	1   ; integer input
