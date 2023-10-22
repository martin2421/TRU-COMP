from Crypto.Cipher import AES

# input data to encrypt and key (must be 16 bytes)
data = input("Enter text to encrypt: ")
key = input("Enter 16 characters for your key: ")

# convert data and key to bytes
b_data = bytes(data, 'utf-8')
b_key = bytes(key, 'utf-8')

# creates an AES cipher object for 
# encryption using the provided key
encode_cipher = AES.new(b_key, AES.MODE_EAX)

# encrypt data
encode_data = encode_cipher.encrypt(b_data)

# create an AES cipher object for 
# decryption using the same key and nonce
decode_cipher = AES.new(b_key, AES.MODE_EAX, encode_cipher.nonce)

# decrypt the encrypted data
decode_data = decode_cipher.decrypt(encode_data)

# output
print("Encrypted data: ", encode_data)
print("Decoded data: ", decode_data)
