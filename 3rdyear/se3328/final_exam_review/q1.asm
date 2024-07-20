%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

fog:
    push ebp
    mov ebp, esp
    sub esp, 4

    xor ebx, ebx
    mov ebx, [ebp + 8]
    add ebx, [ebp + 12]
    mov [ebp - 4], ebx
    mov eax, [ebp - 4]
    sub eax, 2

    mov esp, ebp
    pop ebp
    ret

main:
    push ebp
    mov ebp, esp

    push 5
    push 20
    call fog
    add esp, 8

    call print_int
    call print_nl

    leave
    ret

section .bss
section .data
