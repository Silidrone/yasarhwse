%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

inputArray:
    push ebp
    mov ebp, esp
    
    mov ecx, [ebp + 8]

    mov ebx, [ebp + 12]
    shl ebx, 2
    add ebx, ecx

    ;ecx is now the beginning of the passed memory segment
    ;ebx is now the end of the passed memory segment

    read_input_loop:
        mov eax, msg1
        call print_string
        call read_int

        push eax ;save eax input value AND put it as arg1 for findValue (i.e. the number to find)        
        mov edx, ecx
        sub edx, [ebp + 8]
        push edx ;the number of tries, here it is ecx - [ebp + 8] which is basically how far we have filled the passed array
        push dword [ebp + 8] ;the array to search in (arg1)
        call findValue
        add esp, 8
        pop edx ;restore input value (old eax) to edx, as eax now contains as the return value of findValue

        cmp eax, 0
        jnz try_again ;value found, don't allow duplicate values so jump to try_again
        

        ; value not found, continue...
        mov [ecx], edx
        add ecx, 4
        cmp ecx, ebx
        jnz read_input_loop
        
    pop ebp
    ret

    try_again:
        mov eax, msg3
        call print_string
        call print_nl
        jmp read_input_loop

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

findValue:
    push ebp
    mov ebp, esp

    push ebx
    push ecx
    push edx

    mov ebx, [ebp + 12] ;ebx = arg2 == maximum number of attempts starting from beginning
    cmp ebx, 0
    je retzero

    mov ecx, [ebp + 8] ;ecx = arg1 == the address of the begging of our search sequence

    shl ebx, 2
    add ebx, ecx ;ebx is now the the address of the end of our search sequence

    mov edx, [ebp + 16] ;[ebp + 16] == arg3 == the value we want to find

    find_val:
        cmp [ecx], edx
        je value_found

        mov ebx, [ebp + 12]
        shl ebx, 2
        add ebx, [ebp + 8]

        add ecx, 4
        cmp ecx, ebx
        jnz find_val

    retzero:
        mov eax, 0
        jmp leave_func
    
    value_found:
        mov eax, ecx
        jmp leave_func
    
    leave_func:
        pop edx
        pop ecx
        pop ebx
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
    msg3 db "Value already entered, try again!", 0
    comma_msg db ", ", 0
