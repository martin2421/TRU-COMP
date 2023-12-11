from Crypto.PublicKey import RSA
from Crypto.Random import get_random_bytes
from Crypto.Cipher import AES, PKCS1_OAEP
from tkinter import filedialog

# asks user to open text file
filename = filedialog.askopenfilename(
    filetypes=(
        ("Text files", "*.txt"),
    )
)

# checks if a file was selected
if filename:
     file = open(filename, "r")
     data = file.read().encode("utf-8")
     
     # creates a new RSA key pair, size 2048 bits
     key = RSA.generate(2048)
     
     # private key creation
     private_key = key.export_key()
     file_out = open("private_key.pem", "wb")
     file_out.write(private_key)
     file_out.close()
     
     # public key creation
     public_key = key.public_key().export_key()
     file_out = open("receiver_key.pem", "wb")
     file_out.write(public_key)
     file_out.close()
     
     # open a new file name for writing
     file_out = open("encrypted_data.bin", "wb")
     
     # generate a random 16-byte session key
     recipient_key = RSA.import_key(open("receiver_key.pem").read())
     session_key = get_random_bytes(16)
     
     # encrypt the session key with the public RSA key
     cipher_rsa = PKCS1_OAEP.new(recipient_key)
     enc_session_key = cipher_rsa.encrypt(session_key) 
     
     # encrypt the data with the AES session key
     cipher_aes = AES.new(session_key, AES.MODE_EAX)
     ciphertext, tag = cipher_aes.encrypt_and_digest(data)
     [ file_out.write(x) for x in (enc_session_key, cipher_aes.nonce, tag, ciphertext) ]
     file_out.close()
     
     # open file to read session key, nonce, tag, and ciphertext
     file_in = open("encrypted_data.bin", "rb")
     
     # imports private key and reads the encrypted content from file
     private_key = RSA.import_key(open("private_key.pem").read()) 
     enc_session_key, nonce, tag, ciphertext = \
   [ file_in.read(x) for x in (private_key.size_in_bytes(), 16, 16, -1) ] 
     file_in.close()
     
     # decrypt the session key with the private RSA key
     cipher_rsa = PKCS1_OAEP.new(private_key)
     session_key = cipher_rsa.decrypt(enc_session_key)
     
     # decrypt the data with the AES session key
     cipher_aes = AES.new(session_key, AES.MODE_EAX, nonce)
     data = cipher_aes.decrypt(ciphertext).decode("utf-8")  
     
     # output encrypted text and decrypted text
     print("\nCiphertext: ", ciphertext)
     encrypted_text_file = open("Encrypted_Text.txt", "w")
     encrypted_text_file.write("Ciphertext:\n" + str(ciphertext))
     encrypted_text_file.close()
     
     print("\nDecrypted data: ", data)
     decrypted_text_file = open("Decrypted_Text.txt", "w")
     decrypted_text_file.write("Decrypted data:\n" + str(data))
     decrypted_text_file.close()

# no file selected     
else:
     print("No File Selected.")
