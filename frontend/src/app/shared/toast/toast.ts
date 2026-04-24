import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastService, Toast } from '../../services/toast';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.component.html'
})
export class ToastComponent {

  toast: Toast | null = null;

  constructor(private toastService: ToastService) {
    this.toastService.toast$.subscribe(t => {
      this.toast = t;
    });
  }
}