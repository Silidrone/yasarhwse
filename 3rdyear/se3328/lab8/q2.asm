%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp
    
    xor eax, eax
    mov ebx, 0
    start_loop:
        cmp ebx, 4
        je end
        cmp ebx, 3
        jle print_i
    
    print_i:
        mov eax, ebx
        call print_int
        inc ebx
        jmp start_loop
    
    end:
        leave
        ret
    
section .data

section .bss
