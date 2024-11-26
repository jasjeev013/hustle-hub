import { Component } from '@angular/core';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css'],
})
export class ContactUsComponent {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  message: string = '';

  submitForm(): void {
    if (this.firstName && this.lastName && this.email) {
      alert(
        `Thank you, ${this.firstName}! Your message has been received. We'll get back to you shortly.`
      );
      // Reset the form fields
      this.firstName = '';
      this.lastName = '';
      this.email = '';
      this.message = '';
    } else {
      alert('Please fill out all required fields.');
    }
  }
}
