import { Component, HostListener } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-sliding-card',
  templateUrl: './sliding-card.component.html',
  styleUrls: ['./sliding-card.component.css'],
  animations: [
    trigger('slideIn', [
      state('hidden', style({
        opacity: 0,
        transform: 'translateY(50px)'
      })),
      state('visible', style({
        opacity: 1,
        transform: 'translateY(0)'
      })),
      transition('hidden => visible', [
        animate('0.5s ease-out')
      ]),
      transition('visible => hidden', [
        animate('0.5s ease-in')
      ])
    ])
  ]
})
export class SlidingCardComponent {
  cardState = ['hidden', 'hidden', 'hidden'];

  // Detect scroll event
  @HostListener('window:scroll', [])
  onWindowScroll() {
    const cards = document.querySelectorAll('.big-card');
    cards.forEach((card, index) => {
      const rect = card.getBoundingClientRect();
      if (rect.top <= window.innerHeight - 100) {
        this.cardState[index] = 'visible';
      }
    });
  }

  // Hover effect
  hovered(cardIndex: number) {
    this.cardState = this.cardState.map((_, index) =>
      index === cardIndex - 1 ? 'hovered' : 'visible'
    );
  }
}
