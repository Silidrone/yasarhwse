class min_heap {
private:
    int *_heap;
    int _end;
    int _capacity;

    bool valid(int i) {
        return i >= 0 && i < _end;
    }

    int left(int index) {
        return 2 * index + 1;
    }

    int right(int index) {
        return 2 * index + 2;
    }

    int parent(int index) {
        return (index - 1) / 2;
    }

    void swap(int i, int j) {
        int tmp = _heap[i];
        _heap[i] = _heap[j];
        _heap[j] = tmp;
    }
public:
    min_heap() {
        _capacity = 10;
        _heap = new int[_capacity];
        _end = 0;
    }

    ~min_heap() {
        delete[] _heap;
    }

    void insert(int n) {
        if(_end == _capacity) {
            int *tmp = _heap;
            _heap = new int[_capacity * 2];

            for(int i = 0; i < _capacity; i++) {
                _heap[i] = tmp[i];
            }

            _capacity *= 2;
            delete[] tmp;
        }

        _heap[_end++] = n;
        swim();
    }

    int poll() {
        if(empty()) throw std::logic_error("The heap is empty, you cannot poll anything!");

        int min = _heap[0];
        swap(0, --_end);
        sink();
        return min;
    }

    void swim() {
        int i = _end - 1;
        while(valid(parent(i)) && _heap[i] < _heap[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    void sink() {
        int i = 0;

        while(valid(left(i))) {
            int min_child_i = left(i);
            if(valid(right(i)) && _heap[right(i)] < _heap[left(i)]) {
                min_child_i = right(i);
            }

            if(_heap[min_child_i] < _heap[i]) {
                swap(min_child_i, i);
                i = min_child_i;
            } else {
                break;
            }
        }
    }

    bool empty() {
        return _end == 0;
    }
};
