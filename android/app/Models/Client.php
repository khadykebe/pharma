<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Client extends Model
{
    use HasFactory;
    protected $fillable = [
        'nom',
        'prenom',
        'adresse',
        'telephone',
    ];

    public function client()
    {
        return $this->hasMany(Vente::class);
    }
}
