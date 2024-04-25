%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    askforinput:
        mov eax, msg1
        call print_string
        call read_int
        
        cmp eax, 020h ; is white space?
        je .iswhitespace

        cmp eax, 030h ; is digit?
        jl .isnotascii

        loop askforinput

    iswhitespace:
        mov eax, msg2
        call print_string
    
    leave
    ret

section .data
    msg1        db  "Enter an integer: ", 0
    msg2        db  "It's the ASCII code for a white space.", 0
    msg3        db  "It's the ASCII code for a digit.", 0
    msg4        db  "It's some non-extended ASCII code.", 0
    msg5        db  "It's some extended ASCII code.", 0
    msg6        db  "It's not an ASCII code.", 0
    msg7        db  "Negative integer given, program exitting...", 0
