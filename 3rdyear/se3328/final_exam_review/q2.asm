%include "../linux-ex/asm_io.inc"

extern printf

section .text

global main


;takes two arguments, an address and a number, respectively. Prompts user for n number of integers and saves them consecutively starting from address.
inputArray:
    push ebp
    mov ebp, esp

    

    pop ebp
    ret
main:
    enter 0, 0
    pusha
    


    popa
    mov eax, 0
    leave
    ret

section .bss:
    arr resd 10
section .data:
    msg1 db "Enter an integer: ", 0
    msg2 db "Value already entered, try again!", 0
    msg3 db "List: ", 0
    