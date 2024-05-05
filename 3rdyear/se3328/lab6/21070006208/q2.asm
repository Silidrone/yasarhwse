%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    askforinput:
        xor ebx, ebx
        mov eax, msg1
        call print_string
        call read_int
        mov ecx, eax

        cmp eax, 000h
        jl isnegativeint
        
        divbytwo:
            mov bl, 2
            div bl
            cmp ah, 0
            je bytwoinc

        divbythree:
            mov eax, ecx
            mov bl, 3
            div bl
            cmp ah, 0
            je bythreeinc

        divbyfive:
            mov eax, ecx
            mov bl, 5
            div bl
            cmp ah, 0
            je byfiveinc

        loop askforinput

    bytwoinc:
        mov ebx, [bytwo]
        inc ebx
        mov [bytwo], ebx
        jmp divbythree

    bythreeinc:
        mov ebx, [bythree]
        inc ebx
        mov [bythree], ebx
        jmp divbyfive

    byfiveinc:
        mov ebx, [byfive]
        inc ebx
        mov [byfive], ebx
        jmp askforinput

    isnegativeint:
        mov eax, msg2
        call print_string
        movzx eax, byte [bytwo]
        call print_int

        call print_nl

        mov eax, msg3
        call print_string
        movzx eax, byte [bythree]
        call print_int

        call print_nl

        mov eax, msg4
        call print_string
        movzx eax, byte [byfive]
        call print_int

        call print_nl

    leave
    ret

section .data
    msg1        db  "Enter an integer: ", 0
    msg2        db  "Number of entered integers divisible by 2: ", 0
    msg3        db  "Number of entered integers divisible by 3: ", 0
    msg4        db  "Number of entered integers divisible by 5: ", 0
    bytwo       db  0
    bythree     db  0
    byfive      db  0
section .bss
    input1  resd    1
