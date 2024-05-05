%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main

main:
    push ebp
    mov ebp, esp

    while:
        mov eax, msg
        call print_string
        call read_int
        mov ebx, eax
        cmp eax, 0
        je end_while
        mov eax, outmsg
        call print_string
        
        mov ecx, 8
        loop_start:
            mov eax, 4
            mov edx, 0
            loop2_start:
                sal ebx, 1
                adc edx, 0
                cmp eax, 1
                je loop2_end
                sal edx, 1
                dec eax
                jmp loop2_start            
      
            loop2_end:                            
                mov eax, edx
                cmp eax, 9
                jg print_hex_as_letter
                call print_int
                loop loop_start
                
        call print_nl
        jmp while
        
        print_hex_as_letter:
            sub eax, 10
            add eax, 65
            call print_char
            sub ecx, 1
            cmp ecx, 0
            jg loop_start
            call print_nl
            jmp while
		
    end_while:
        leave
        ret
section .data
    msg db "Enter an integer: ", 0
    outmsg db "Hex representation: ", 0
