import gdb

# Define a GDB command to display register value in binary format
class BinaryRegisterCommand(gdb.Command):
    def __init__(self):
        super(BinaryRegisterCommand, self).__init__("bireg", gdb.COMMAND_DATA)

    def invoke(self, arg, from_tty):
        # Get the register value
        reg_value = gdb.parse_and_eval(arg)

        # Convert the register value to binary string
        binary_str = "{0:0{1}b}".format(int(reg_value) & ((1 << reg_value.type.sizeof * 8) - 1), reg_value.type.sizeof * 8)

        # Print the binary representation
        print("{}: {}".format(arg, binary_str))

# Register the command
BinaryRegisterCommand()
