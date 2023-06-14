import { isEmpty } from '@shared/model';

export interface Note extends isEmpty {
  note_id: string;
  cursus_name: string;
  note: string;
}
