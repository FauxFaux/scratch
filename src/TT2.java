import java.util.Arrays;


public class TT2 {
	static class Exit1 extends RuntimeException {}
	static class Exit2 extends RuntimeException {}
	static char in1[] = new char[] {
			0x08,0x63,0x65,0x72,0x61,0x6E,0x6B,0x61,0x73,0x00,0xA0,0x00,0x18,0xC3,0x46,0x00,
			0x00,0xA5,0x5F,0x1B,0x00,0x50,0x4C,0x00,0x00,0x35,0x0C,0x00,0x00,0x45,0x6E,0x6F,
			0x20,0x4B,0x68,0x61,0x6F,0x6E,0xA0,0x1F,0x04,0x69,0x47,0x00,0x00,0x5E,0x20,0x1F,
			0x0B,0x55,0x53,0x00,0x00,0x49,0x07,0x00,0x00,0x42,0x69,0x76,0x65,0xC0,0x1B,0x40,
			0x00,0x09,0x64,0x51,0x00,0x00,0x51,0x60,0x1B,0x00,0x52,0x55,0x20,0x07,0x08,0x1D,
			0x00,0x00,0x53,0x69,0x6C,0x76,0x65,0x72,0x40,0x19,0x80,0x00,0x04,0x22,0x5D,0x00,
			0x00,0x52,0x20,0x1F,0x04,0x47,0x42,0x01,0x00,0x5A,0x20,0x1F,0x03,0x73,0x69,0x65,
			0x63,0x20,0x02,0x00,0x68,0x80,0x1D,0x11,0x00,0x00,0x8D,0x61,0x00,0x00,0x10,0x60,
			0x1B,0x00,0x50,0x4C,0x00,0x00,0x18,0x15,0x00,0x00,0x00,0x00,
	};

	static char out1[] = new char[] {
			0x63,0x65,0x72,0x61,0x6E,0x6B,0x61,0x73,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0xC3,0x46,0x00,0x00,0xA5,0x5F,0x1B,0x00,0x50,0x4C,0x00,0x00,0x35,0x0C,0x00,0x00,
			0x45,0x6E,0x6F,0x20,0x4B,0x68,0x61,0x6F,0x6E,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x69,0x47,0x00,0x00,0x5E,0x5F,0x1B,0x00,0x55,0x53,0x00,0x00,0x49,0x07,0x00,0x00,
			0x42,0x69,0x76,0x65,0x6E,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x64,0x51,0x00,0x00,0x51,0x60,0x1B,0x00,0x52,0x55,0x00,0x00,0x51,0x1D,0x00,0x00,
			0x53,0x69,0x6C,0x76,0x65,0x72,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x22,0x5D,0x00,0x00,0x52,0x60,0x1B,0x00,0x47,0x42,0x01,0x00,0x5A,0x1D,0x00,0x00,
			0x73,0x69,0x65,0x63,0x69,0x65,0x63,0x68,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x8D,0x61,0x00,0x00,0x10,0x60,0x1B,0x00,0x50,0x4C,0x00,0x00,0x18,0x15,0x00,0x00,
	};

	public static void main(String[] args) {
		if (!Arrays.equals(decode(in1), out1))
			System.out.println("FAIL");
	}

	private static char[] decode(char[] in) {
		char[] out = new char[200];
		for (int i = 0; i < out.length; ++i)
			out[i] = Character.MAX_VALUE;
		int outptr = 0;
//		register save
//		0041EBA0  /$ 83EC 18        SUB ESP,18                               ;  decode routine (1?)
//		0041EBA3  |. 53             PUSH EBX
//		0041EBA4  |. 55             PUSH EBP
//		0041EBA5  |. 56             PUSH ESI

//		Output address:
//		0041EBA6  |. 8B7424 28      MOV ESI,DWORD PTR SS:[ESP+28]            ;  output address

//		Input address:
//		0041EBAA  |. 57             PUSH EDI
//		0041EBAB  |. 8BFA           MOV EDI,EDX

//		End of decompression buffer:
//		0041EBAD  |. 8D140E         LEA EDX,DWORD PTR DS:[ESI+ECX]


//		0041EBB0  |. 33C9           XOR ECX,ECX
//		0041EBB2  |. 8A0F           MOV CL,BYTE PTR DS:[EDI]
		// ecx = first byte


//		0041EBB4  |. 03C7           ADD EAX,EDI
		// eax == end pointer, i.e. edi == in.length

//		This putting stuff in places is getting on my nerves

//		0041EBB6  |. 894424 24      MOV DWORD PTR SS:[ESP+24],EAX
		char esp24 = (char) in.length, eax = (char) in.length;

		// edx still points to junk
//		0041EBBA  |. 895424 1C      MOV DWORD PTR SS:[ESP+1C],EDX
		char esp1c = Character.MAX_VALUE;

//		0041EBBE  |. C74424 14 0100>MOV DWORD PTR SS:[ESP+14],1
		char esp14 = 1;

//		Fill ebp and save it random places again


//		0041EBC6  |. 83E1 1F        AND ECX,1F
//		0041EBC9  |. 8BE9           MOV EBP,ECX
//		0041EBCB  |. 896C24 10      MOV DWORD PTR SS:[ESP+10],EBP
//		0041EBCF  |. 47             INC EDI
		// ecx was read from in[0], going to use ebp flag.
		int inptr = 0;
		char flag = in[inptr++];
		flag &= 0x1f;
		char esp10 = flag;

//		ebp is the control byte, <0x20 == three clear bits
//		0041EBD0  |> 83FD 20        /CMP EBP,20
//		0041EBD3  |. 0F82 2E010000  |JB truck.0041ED07 (easymode)
		while (true) {
			if (flag >= 0x20) {

				// Get the control byte >> 5 and << 8.
	//			0041EBD9  |. 8BDD           |MOV EBX,EBP
	//			0041EBDB  |. 8BD5           |MOV EDX,EBP
	//			0041EBDD  |. 83E3 1F        |AND EBX,1F
	//			0041EBE0  |. C1EA 05        |SHR EDX,5
	//			0041EBE3  |. C1E3 08        |SHL EBX,8
				char rightfive = (char) (flag >> 5);
				int someoffset = (flag & 0x1f) << 8;

//				Using ecx as esi..
//				0041EBE6  |. 8BCE           |MOV ECX,ESI

//				>> 5'd byte -1.
//				0041EBE8  |. 4A             |DEC EDX
				--rightfive;

//				ebx is 0 in the example but I don't know why
//				0041EBE9  |. 2BCB           |SUB ECX,EBX

//				Not sure when this happens, either.
//				0041EBEB  |. 83FA 06        |CMP EDX,6
//				0041EBEE  |. 75 07          |JNZ SHORT truck.0041EBF7
				if (6 == rightfive) {
//					0041EBF0  |. 0FB617         |MOVZX EDX,BYTE PTR DS:[EDI]
//					0041EBF3  |. 83C2 06        |ADD EDX,6
//					0041EBF6  |. 47             |INC EDI
					throw new AssertionError();
				}

//				default (fallthrough else):
//				0041EBF7  |> 0FB61F         |MOVZX EBX,BYTE PTR DS:[EDI]
//				0041EBFA  |. 2BCB           |SUB ECX,EBX
//				0041EBFC  |. 47             |INC EDI
				someoffset -= in[inptr++];

				int someptr = outptr + someoffset;

//				What's going on. :/
//				0041EBFD  |. 8D5C32 03      |LEA EBX,DWORD PTR DS:[EDX+ESI+3]
				int ptr2 = outptr + rightfive;
//				0041EC01  |. 3B5C24 1C      |CMP EBX,DWORD PTR SS:[ESP+1C]
//				0041EC09  |. 0F87 42010000  |JA truck.0041ED51 (exit)
				if (ptr2 >= out.length)
					throw new Exit1();

//				0041EC05  |. 897C24 18      |MOV DWORD PTR SS:[ESP+18],EDI
				int esp18 = inptr;

//				What's this condition?
//				0041EC0F  |. 8D59 FF        |LEA EBX,DWORD PTR DS:[ECX-1]
//				0041EC12  |. 3B5C24 2C      |CMP EBX,DWORD PTR SS:[ESP+2C]
//				0041EC16  |. 0F82 35010000  |JB truck.0041ED51 (exit)
				// more boring range checks, cba

//				This one must be some kind of ending check, but eax doesn't really make sense
//				0041EC1C  |. 3BF8           |CMP EDI,EAX
//				0041EC1E  |. 73 0E          |JNB SHORT truck.0041EC2E
				if (inptr < in.length) {

	//				Load the next control byte.
	//				0041EC20  |. 0FB62F         |MOVZX EBP,BYTE PTR DS:[EDI]
	//				0041EC23  |. 47             |INC EDI
					flag = in[inptr++];

	//				WRITING TO PLACES
	//				0041EC24  |. 896C24 10      |MOV DWORD PTR SS:[ESP+10],EBP
					esp10 = flag;
	//				0041EC28  |. 897C24 18      |MOV DWORD PTR SS:[ESP+18],EDI
					esp18 = inptr;
	//				0041EC2C  |. EB 08          |JMP SHORT truck.0041EC36
				} else {
//					WHAT
//					0041EC2E  |> C74424 14 0000>|MOV DWORD PTR SS:[ESP+14],0
					throw new AssertionError();
				}


//				Here's the offset user; ecx is the possibly offset version of esi by here
//				0041EC36  |> 3BCE           |CMP ECX,ESI
//				0041EC38  |. 75 47          |JNZ SHORT truck.0041EC81
				if (outptr == someptr) {

//					Offset is 0, can do it the sane way (ecx = esi remember)
//					0041EC3A  |. 8A41 FF        |MOV AL,BYTE PTR DS:[ECX-1]
					char thing = out[outptr-1];

//					Duplicate the last byte three times
//					0041EC3D  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC3F  |. 46             |INC ESI
//					0041EC40  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC42  |. 46             |INC ESI
//					0041EC43  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC45  |. 46             |INC ESI
					out[outptr++] = thing;
					out[outptr++] = thing;
					out[outptr++] = thing;

//					HATE
//					0041EC46  |. 85D2           |TEST EDX,EDX
//					0041EC48  |. 0F84 F2000000  |JE truck.0041ED40 (continuation)
					if (rightfive != 0) {

//						AAAAAARGH
//						0041EC4E  |. 8BCA           |MOV ECX,EDX
//						0041EC50  |. 895424 20      |MOV DWORD PTR SS:[ESP+20],EDX
//						esp20 = rightfive; XXX NO ESP+20?!
//						0041EC54  |. 8AD0           |MOV DL,AL
						// Assumably here edx (rightfive) is clear apart from the lows..
						// al is the byte we were duplicating, copy it into dl..
//						0041EC56  |. 8B6C24 10      |MOV EBP,DWORD PTR SS:[ESP+10]
						flag = esp10;

//						0041EC5A  |. 8AF2           |MOV DH,DL
						// I have no idea what that just did
//						0041EC5C  |. 8BD9           |MOV EBX,ECX
						// ebx = rightfive;
//						0041EC5E  |. C1E9 02        |SHR ECX,2
						// ecx = rightfive >> 2;
//						0041EC61  |. 8BFE           |MOV EDI,ESI
						// edi = inptr;
//						0041EC63  |. 8BC2           |MOV EAX,EDX
						// eax = fucked up dl dh copied byte doubled
//						0041EC65  |. C1E0 10        |SHL EAX,10
						// massively scary dh dl << 10
//						0041EC68  |. 66:8BC2        |MOV AX,DX
						// one end of eax = dh dl



						// so.. eax is q = (in << 8) | in; q << 10 | q?



//						0041EC6B  |. 8BD3           |MOV EDX,EBX
						// edx = rightfive;

//						Duplicate four bytes of output from somewhere
//						0041EC6D  |. F3:AB          |REP STOS DWORD PTR ES:[EDI]
						// ecx is still rightfive >> 2
						// dword == 4

						for (int i = 0; i < 4*(rightfive>>2); ++i)
							if (thing != 0)
								throw new AssertionError();
							else
								out[outptr + i] = 0;

//						0041EC6F  |. 8BCB           |MOV ECX,EBX
//						0041EC71  |. 83E1 03        |AND ECX,3
						// ecx = first two bits of righfive, i.e. the ones we didn't shift away
//						And another byte, probably?
//						0041EC74  |. F3:AA          |REP STOS BYTE PTR ES:[EDI]
						for (int i = 0; i < (rightfive & 3); ++i)
							if (thing != 0)
								throw new AssertionError();
							else
								out[outptr + i] = 0;

//						0041EC76  |. 8B7C24 18      |MOV EDI,DWORD PTR SS:[ESP+18]
						// restore edi to inptr

//						0041EC7A  |. 03F2           |ADD ESI,EDX
						outptr += rightfive;

//						0041EC7C  |. E9 BF000000    |JMP truck.0041ED40 (continuation)
					}
				} else {
//					This is the non-zero-offset version of the above
//					0041EC81  |> 8A41 FF        |MOV AL,BYTE PTR DS:[ECX-1]
//					0041EC84  |. 49             |DEC ECX
					--someptr;

//					Duplicate the three relevant bytes (ecx = offset, esi = output)
//					0041EC85  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC87  |. 8A41 01        |MOV AL,BYTE PTR DS:[ECX+1]
//					0041EC8A  |. 46             |INC ESI
//					0041EC8B  |. 41             |INC ECX
//					0041EC8C  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC8E  |. 8A41 01        |MOV AL,BYTE PTR DS:[ECX+1]
//					0041EC91  |. 46             |INC ESI
//					0041EC92  |. 41             |INC ECX
//					0041EC93  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//					0041EC95  |. 46             |INC ESI
//					0041EC96  |. 41             |INC ECX
					out[outptr++] = out[someptr++];
					out[outptr++] = out[someptr++];
					out[outptr++] = out[someptr++];

//					0041EC97  |. F6C2 01        |TEST DL,1
//					0041EC9A  |. 74 07          |JE SHORT truck.0041ECA3 (skip random byte)
					if ((rightfive & 1) == 1) {
//						Another byte for some reason.
//						0041EC9C  |. 8A01           |MOV AL,BYTE PTR DS:[ECX]
//						0041EC9E  |. 8806           |MOV BYTE PTR DS:[ESI],AL
//						0041ECA0  |. 46             |INC ESI
//						0041ECA1  |. 41             |INC ECX
						out[outptr++] = out[someptr++];
//
//						WHAT
//						0041ECA2  |. 4A             |DEC EDX
						--rightfive;
					}

//					(skip random byte jump)
//					ARRRRRRRGH 2
//					0041ECA3  |> 8BC6           |MOV EAX,ESI
					int tooutptr = outptr;
//					0041ECA5  |. 03F2           |ADD ESI,EDX
					outptr += rightfive;
//					0041ECA7  |. D1EA           |SHR EDX,1
					rightfive >>= 1;
//					0041ECA9  |. 83FA 04        |CMP EDX,4
//					0041ECAC  |. 76 44          |JBE SHORT truck.0041ECF2
					if (rightfive > 4) {
						// this looks like a partially unrolled loop with the next bit
//						0041ECAE  |. 8D5A FB        |LEA EBX,DWORD PTR DS:[EDX-5]
//						0041ECB1  |. C1EB 02        |SHR EBX,2
//						0041ECB4  |. 43             |INC EBX
//						0041ECB5  |> 66:8B29        |/MOV BP,WORD PTR DS:[ECX]
//						0041ECB8  |. 66:8928        ||MOV WORD PTR DS:[EAX],BP
//						0041ECBB  |. 66:8B69 02     ||MOV BP,WORD PTR DS:[ECX+2]
//						0041ECBF  |. 83C1 02        ||ADD ECX,2
//						0041ECC2  |. 83C0 02        ||ADD EAX,2
//						0041ECC5  |. 66:8928        ||MOV WORD PTR DS:[EAX],BP
//						0041ECC8  |. 66:8B69 02     ||MOV BP,WORD PTR DS:[ECX+2]
//						0041ECCC  |. 83C1 02        ||ADD ECX,2
//						0041ECCF  |. 83C0 02        ||ADD EAX,2
//						0041ECD2  |. 66:8928        ||MOV WORD PTR DS:[EAX],BP
//						0041ECD5  |. 66:8B69 02     ||MOV BP,WORD PTR DS:[ECX+2]
//						0041ECD9  |. 83C1 02        ||ADD ECX,2
//						0041ECDC  |. 83C0 02        ||ADD EAX,2
//						0041ECDF  |. 66:8928        ||MOV WORD PTR DS:[EAX],BP
//						0041ECE2  |. 83C0 02        ||ADD EAX,2
//						0041ECE5  |. 83C1 02        ||ADD ECX,2
//						0041ECE8  |. 83EA 04        ||SUB EDX,4
//						0041ECEB  |. 4B             ||DEC EBX
//						0041ECEC  |.^75 C7          |\JNZ SHORT truck.0041ECB5
//
//						And it continues not making any sense...
//						0041ECEE  |. 8B6C24 10      |MOV EBP,DWORD PTR SS:[ESP+10]
						throw new AssertionError();
					}
//					0041ECF2  |> 85D2           |TEST EDX,EDX
//					0041ECF4  |. 74 4A          |JE SHORT truck.0041ED40 (continuation)
					while (rightfive != 0) {
						// ecx is someptr
//						0041ECF6  |> 66:8B19        |/MOV BX,WORD PTR DS:[ECX]
//						0041ECF9  |. 66:8918        ||MOV WORD PTR DS:[EAX],BX
//						0041ECFC  |. 83C0 02        ||ADD EAX,2
//						0041ECFF  |. 83C1 02        ||ADD ECX,2
						out[tooutptr++] = out[someptr++];
						out[tooutptr++] = out[someptr++];

//						0041ED02  |. 4A             ||DEC EDX
//						0041ED03  |.^75 F1          |\JNZ SHORT truck.0041ECF6
						--rightfive;
					}
//					0041ED05  |. EB 39          |JMP SHORT truck.0041ED40 (continuation)
				}
			} else {
				//Here's the easymode version.  Still makes no sense
	//			0041ED07  |> 8B5424 1C      |MOV EDX,DWORD PTR SS:[ESP+1C]
				// edx = end of decompression buffer

	//			0041ED0B  |. 45             |INC EBP
				++flag;
	//			0041ED0C  |. 8D0C2E         |LEA ECX,DWORD PTR DS:[ESI+EBP]
	//			0041ED0F  |. 3BCA           |CMP ECX,EDX
	//			0041ED11  |. 77 3E          |JA SHORT truck.0041ED51 (exit)
				int outend = outptr + flag;
				if (outend >= out.length)
					throw new Exit1();

	//			0041ED13  |. 8D142F         |LEA EDX,DWORD PTR DS:[EDI+EBP]
	//			0041ED16  |. 3BD0           |CMP EDX,EAX
	//			0041ED18  |. 77 37          |JA SHORT truck.0041ED51 (exit)
				int inend = inptr + flag;
				if (inend >= in.length)
					return Arrays.copyOfRange(out, 0, outptr);

				//MEMCPY ebpish bytes from edi to esi, good.
	//			0041ED1A  |. 8A0F           |MOV CL,BYTE PTR DS:[EDI]
	//			0041ED1C  |. 880E           |MOV BYTE PTR DS:[ESI],CL
	//			0041ED1E  |. 46             |INC ESI
	//			0041ED1F  |. 47             |INC EDI
	//			0041ED20  |. 4D             |DEC EBP
	//			0041ED21  |. 74 09          |JE SHORT truck.0041ED2C
	//			0041ED23  |> 8A17           |/MOV DL,BYTE PTR DS:[EDI]
	//			0041ED25  |. 8816           ||MOV BYTE PTR DS:[ESI],DL
	//			0041ED27  |. 46             ||INC ESI
	//			0041ED28  |. 47             ||INC EDI
	//			0041ED29  |. 4D             ||DEC EBP
	//			0041ED2A  |.^75 F7          |\JNZ SHORT truck.0041ED23                        ; blatent memcpy
				for (int i = 0; i < flag; ++i)
					out[outptr++] = in[inptr++];

				//I think eax has the end of stream pointer in it here, and it's checking for end
	//			0041ED2C  |> 3BF8           |CMP EDI,EAX
	//
	//			set the exit code and exit:
	//			0041ED2E  |. 1BC0           |SBB EAX,EAX
	//			0041ED30  |. F7D8           |NEG EAX
	//			0041ED32  |. 894424 14      |MOV DWORD PTR SS:[ESP+14],EAX

//				esp14 = something;
//	//			0041ED36  |. 74 23          |JE SHORT truck.0041ED5B (exit 2)
//				if (inptr == something)
//					throw new Exit2(); XXX

				//Take the next byte from the input into the control code...
	//			0041ED38  |. 0FB62F         |MOVZX EBP,BYTE PTR DS:[EDI]
	//
	//			...putting it somewhere:
	//			0041ED3B  |. 896C24 10      |MOV DWORD PTR SS:[ESP+10],EBP
	//			0041ED3F  |. 47             |INC EDI
				flag = in[inptr++];
				esp10 = flag;

			}
	//			(continuation)
	//			Load eax from SOMEWHERE.
	//			0041ED40  |> 8B4424 14      |MOV EAX,DWORD PTR SS:[ESP+14]
	//			HATEful way of checking exit
	//			0041ED44  |. 85C0           |TEST EAX,EAX
	//			0041ED46  |. 74 13          |JE SHORT truck.0041ED5B (exit 2)
				if (esp14 == 0)
					throw new Exit2();
	//			0041ED48  |. 8B4424 24      |MOV EAX,DWORD PTR SS:[ESP+24]
				// set eax back to the end pointer; irrelevant.

	//		Otherwise, go back to the control code switch
	//		0041ED4C  |.^E9 7FFEFFFF    \JMP truck.0041EBD0
		}

//		(exit)
//		Fixes the registers and leaves, all good, exit == 0.
//		0041ED51  |> 5F             POP EDI
//		0041ED52  |. 5E             POP ESI
//		0041ED53  |. 5D             POP EBP
//		0041ED54  |. 33C0           XOR EAX,EAX
//		0041ED56  |. 5B             POP EBX
//		0041ED57  |. 83C4 18        ADD ESP,18
//		0041ED5A  |. C3             RETN
//
//		(exit 2)
//		Fixes the registers and does something to eax to indicate.. something.
//		0041ED5B  |> 8B4C24 2C      MOV ECX,DWORD PTR SS:[ESP+2C]
//		0041ED5F  |. 5F             POP EDI
//		0041ED60  |. 8BC6           MOV EAX,ESI
//		0041ED62  |. 5E             POP ESI
//		0041ED63  |. 5D             POP EBP
//		0041ED64  |. 2BC1           SUB EAX,ECX
//		0041ED66  |. 5B             POP EBX
//		0041ED67  |. 83C4 18        ADD ESP,18
//		0041ED6A  \. C3             RETN
	}

	private static boolean testcomma1(char rightfive) {
		if (rightfive == 0)
			return true;
		throw new AssertionError();
	}
}
