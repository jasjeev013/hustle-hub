import { Component } from '@angular/core';

@Component({
  selector: 'app-newsletter',
  templateUrl: './newsletter.component.html',
  styleUrls: ['./newsletter.component.css']
})
export class NewsletterComponent {
  email: string = '';

  subscribe(): void {
    if (this.email) {
      alert(`Thank you for subscribing with: ${this.email}`);
      this.email = ''; // Clear the input field
    } else {
      alert('Please enter a valid email address!');
    }
  }

  onFocus(): void {
    console.log('Input field focused');
  }

  onBlur(): void {
    console.log('Input field blurred');
  }
}
