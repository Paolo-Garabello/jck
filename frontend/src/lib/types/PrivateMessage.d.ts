import type { Message } from "./Message";

export interface PrivateMessage extends Message {
    recipient: number;
    id: number;
    sender: number;
    username: string;
    recipientUsername: string;
}
