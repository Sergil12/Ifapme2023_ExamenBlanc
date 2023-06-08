import {isEmpty} from '@shared/model';

export interface Credential extends isEmpty {
  id: number;
  username: string;
  isActif: boolean;
}


