import socket, threading, datetime, time
from dateutil import parser

def send_time(sock):
    while True:
        sock.send(str(datetime.datetime.now()).encode())
        print("Time sent\n")
        time.sleep(5)

def receive_time(sock):
    while True:
        synced = parser.parse(sock.recv(1024).decode())
        print(f"Synchronized time: {synced}\n")

def start_client(port=8080):
    sock = socket.socket()
    sock.connect(('127.0.0.1', port))
    print("Connected to server\n")
    threading.Thread(target=send_time, args=(sock,)).start()
    threading.Thread(target=receive_time, args=(sock,)).start()

if __name__ == '__main__':
    start_client()