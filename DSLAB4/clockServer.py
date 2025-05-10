import socket, threading, datetime, time
from dateutil import parser

client_data = {}

def handle_client(conn, addr):
    while True:
        clock = parser.parse(conn.recv(1024).decode())
        diff = datetime.datetime.now() - clock
        client_data[addr] = {"clock": clock, "diff": diff, "conn": conn}
        print(f"Updated client: {addr}\n")
        time.sleep(5)

def accept_clients(server):
    while True:
        conn, addr = server.accept()
        addr_str = f"{addr[0]}:{addr[1]}"
        print(f"{addr_str} connected")
        threading.Thread(target=handle_client, args=(conn, addr_str)).start()

def get_avg_diff():
    if not client_data: return datetime.timedelta()
    total = sum((c["diff"] for c in client_data.values()), datetime.timedelta())
    return total / len(client_data)

def sync_clocks():
    while True:
        print(f"\nSync cycle started. Clients: {len(client_data)}")
        if client_data:
            avg = get_avg_diff()
            for addr, c in client_data.items():
                try:
                    c["conn"].send(str(datetime.datetime.now() + avg).encode())
                except:
                    print(f"Failed to sync with {addr}")
        else:
            print("No clients connected.")
        time.sleep(5)

def start_server(port=8080):
    server = socket.socket()
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind(('', port))
    server.listen(10)
    print("Clock server running...\n")
    threading.Thread(target=accept_clients, args=(server,)).start()
    threading.Thread(target=sync_clocks).start()

if __name__ == '__main__':
    start_server()
