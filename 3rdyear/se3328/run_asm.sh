#!/bin/bash

if [ -z "$1" ]; then
    echo "Usage: $0 <asm file path>"
    exit 1
fi

sdir=$(dirname "$(realpath "$0")")
fpath=$(dirname "$1")
fname=$(basename "$1")
fnoext="${fname%.*}"

echo $sdir

cd "$fpath"
nasm -f elf32 "$fname" -g -o "${fnoext}.o"
gcc -m32 "${fnoext}.o" "${sdir}/asm_io.o" -o "${fnoext}.out"
./"${fnoext}.out"