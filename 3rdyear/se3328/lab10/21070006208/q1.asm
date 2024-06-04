%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

inputArray:
    push ebp
    mov ebp, esp
    
    mov ebx, [ebp + 8]
    mov ecx, [ebp + 12]
    shl ecx, 2
    add ebx, ecx
    mov ecx, [ebp + 8]

    read_input_loop:
        mov eax, msg1
        call print_string
        call read_int
        mov [ecx], eax

        add ecx, 4
        cmp ecx, ebx
        jnz read_input_loop
        
    pop ebp
    ret
    
print_comma_and_continue:
    mov eax, comma_msg
    call print_string
    jmp print_arr

printArray:
    push ebp
    mov ebp, esp
    
    mov ebx, [ebp + 8]
    mov ecx, [ebp + 12]
    shl ecx, 2
    add ebx, ecx
    mov ecx, [ebp + 8]

    print_arr:
        mov eax, [ecx]
        call print_int
        
        add ecx, 4
        cmp ecx, ebx
        jnz print_comma_and_continue
    
    call print_nl
    
    pop ebp
    ret

main:
    push ebp
    mov ebp, esp

    push dword 10
    push arr
    call inputArray
    add esp, 8


    mov eax, msg2
    call print_string

    push dword 10
    push arr
    call printArray
    add esp, 8

    leave
    ret

section .bss
    arr resd 10 ; an array of 10 32-bit integers
section .data
    msg1 db "Enter an integer: ", 0
    msg2 db "List: ", 0
    comma_msg db ", ", 0
