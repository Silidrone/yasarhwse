%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    while:
        mov  eax, msg
        call print_string
        call read_int
        mov  ebx, eax
        cmp  eax, 0
        je   end_while
        mov  eax, outmsg
        call print_string
        
	mov ecx, 32

	loop_start:
		sal  ebx, 1
        setc al
		movzx  eax, al
		call print_int
		loop loop_start
		
	call print_nl
	jmp  while
		
    end_while:
        leave
        ret

section .data
    msg    db "Enter an integer: ", 0
    outmsg db "Binary representation: ", 0

section .bss
    input1 resb 5
    input2 resb 3
