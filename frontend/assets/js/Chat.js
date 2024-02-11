class Chat {
  /**
   * Chat constructor
   * @param {string} url The WebSocket url (i.e. ws://localhost:8080).
   */
  constructor(url) {
    this.webSocket = new WebSocket(url);
    this.webSocket.onopen = this.onOpen.bind(this);
    this.webSocket.onmessage = this.onMessage.bind(this);
    this.webSocket.onclose = this.onClose.bind(this);
  }

  onOpen(event) {
    console.log("WebSocket connection established.");
  }

  onMessage(event) {
    console.log(event.data);
    this.updateMessages(event.data);
  }

  onClose(event) {
    console.log("WebSocket connection closed.");
  }

  updateMessages(data) {
    data = JSON.parse(data);
    const responseList = document.getElementById("public-chat-messages");
    const newMessage = document.createElement("li");
    newMessage.setAttribute('data-sender', data.name);
    newMessage.setAttribute('data-date', this.getFormattedDate(data.date));
    newMessage.textContent = data.message;
    responseList.appendChild(newMessage);
  }

  getFormattedDate(unix_timestamp) {
    let date = new Date(unix_timestamp);
    let minutes = "0" + date.getMinutes();
    return date.getHours() + ':' + minutes.substring(minutes.length - 2);
  }

  sendMessage(data) {
    this.webSocket.send(JSON.stringify(data));
  }
}
