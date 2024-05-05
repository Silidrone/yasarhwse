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
        je iswhitespace

        jmp cmp1

        loop askforinput

    cmp1:
        cmp eax, 030h ; is digit?
        jge cmp2
        jmp cmp3

    cmp2:
        cmp eax, 039h  
        jle isdigit
        jmp cmp3

    cmp3:
        cmp eax, 000h ; is non-extended?
        jge cmp4
        jmp cmp5
    
    cmp4:
        cmp eax, 07Fh  
        jle isnonextended
        jmp cmp5
    
    cmp5:
        cmp eax, 080h ; is extended?
        jge cmp6
        jmp cmp7

    cmp6:
        cmp eax, 0FFh  
        jle isextended
        jmp cmp7
        
    cmp7:
        cmp eax, 0FFh ; is non-ascii?
        jg isnonascii
        jmp isnegativeint

    iswhitespace:
        mov eax, msg2
        call print_string
        jmp end
    isdigit:
        mov eax, msg3
        call print_string
        jmp end
    isnonextended:
        mov eax, msg4
        call print_string
        jmp end
    isextended:
        mov eax, msg5
        call print_string
        jmp end
    isnonascii:
        mov eax, msg6
        call print_string
        jmp end
    isnegativeint:
        mov eax, msg7
        call print_string
        jmp end
    end:
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
