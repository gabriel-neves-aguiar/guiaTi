import { Component, Input, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-icone-expandir',
  imports: [],
  templateUrl: './icone-expandir.component.html',
  styleUrl: './icone-expandir.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class IconeExpandirComponent {
  @Input() expandido: boolean = false;
  @Input() color: string = 'currentColor';
  @Input() size: string = '1em';
}
