%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    xor ecx, ecx
    mov eax, msg1
    call print_string
    call read_int
    mov edx, eax
    mov ebx, eax

    mov eax, msg2
    call print_string
    mov ecx, 32

	loop_start:
		sal  ebx, 1
        setc al
		movzx  eax, al
		call print_int
		loop loop_start
		
	call print_nl

    mov ebx, edx
    xor edx, edx
    xor ecx, ecx
    mov eax, msg3
    call print_string
    call print_nl

	mov dl, 8
    
    read_person:
        mov eax, four_spaces
        call print_string
        mov dh, 4
        read_codes:
            sal  ebx, 1
            setc al

            cmp al, 0
            je print_code_false
            jmp print_code_true

            print_code_false:
                mov cx, 0
                jmp print_code

            print_code_true:
                mov cx, 5
                jmp print_code

            print_code:
                mov al, 4
                sub al, dh
                mov ah, 10
                mul ah
                add ax, cx
                mov [index_calc], ax
                mov eax, codes
                add eax, [index_calc]
                call print_string

            mov eax, four_spaces
            call print_string
            dec dh
            cmp dh, 0
            jne read_codes
        
        call print_nl
        dec dl
        cmp dl, 0
        jne read_person

    leave
    ret

section .data
    msg1 db "Enter an integer: ", 0
    msg2 db "Binary representation: ", 0
    msg3 db "Semantic: ", 0
    codes db "YNGR", 0, "OLDR", 0, "ANDR", 0, "IPHN", 0, "NFLX", 0, "DISN", 0, "SWRS", 0, "STRK", 0
    four_spaces db "    ", 0
section .bss
    index_calc resw 1 ; only 2 bytes!
    