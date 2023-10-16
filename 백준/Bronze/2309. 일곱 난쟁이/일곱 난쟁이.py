dwarf_list = []

for _ in range(9):
    dwarf_list.append(int(input()))
    
magic_number = sum(dwarf_list) - 100

dwarf_list.sort()
liar_index = []
for first_liar in dwarf_list:
    for second_liar in dwarf_list:
        if second_liar == first_liar:
            continue
        if first_liar + second_liar == magic_number:
            liar_index.append(first_liar)
            liar_index.append(second_liar)

            

for dwarf in dwarf_list:
    if dwarf == liar_index[0] or dwarf == liar_index[1]:
        continue
    print(dwarf,'')