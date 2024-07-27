import React from 'react';

const PersonnelItem = ({ person, onDelete }) => {
    return (
        <li>
            <span>{person.name} - {person.email}</span>
            <button onClick={() => onDelete(person.id)}>Sil</button>
        </li>
    );
};

export default PersonnelItem;